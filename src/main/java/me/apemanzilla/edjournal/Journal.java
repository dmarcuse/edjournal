package me.apemanzilla.edjournal;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import com.google.common.base.Supplier;
import com.google.common.collect.Streams;
import com.google.gson.Gson;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.apemanzilla.edjournal.events.JournalEvent;

/**
 * A player journal, backed by log files from a given directory.<br>
 * Events are streamed using {@link JournalFile} instances from the journal
 * files in the journal's directory.<br>
 * <br>
 * Once you've acquired an instance via {@link Journal#create()} or
 * {@link Journal#create(Path)}, you can use it to stream {@link JournalEvent
 * JournalEvents}.
 * 
 * @author apemanzilla
 *
 */
@Value
@ToString(of = "logDirectory")
public class Journal {
	/**
	 * Creates a new journal that will load logs from the given journal directory.
	 * 
	 * @param logDirectory
	 *            The directory to watch for player journal files
	 * @return A new <code>Journal</code> instance
	 */
	public static Journal create(Path logDirectory) {
		return new Journal(logDirectory, JournalUtils.gson);
	}

	/**
	 * Creates a journal from the {@link JournalUtils#getDefaultJournalDirectory()
	 * default Elite: Dangerous player journal directory}.
	 * 
	 * @return A new <code>Journal</code> instance using the default directory
	 * 
	 * @throws IllegalStateException
	 *             Thrown when the default player journal directory cannot be
	 *             determined (e.g. on Linux)
	 * 
	 * @see JournalUtils#getDefaultJournalDirectory()
	 */
	public static Journal create() throws IllegalStateException {
		return create(JournalUtils.getDefaultJournalDirectory());
	}

	/**
	 * The directory that journal files will be loaded from.
	 * 
	 * @return The directory that journal files will be loaded from.
	 */
	private final Path logDirectory;

	final transient Gson gson;

	Journal(@NonNull Path logDirectory, @NonNull Gson gson) {
		if (!Files.isDirectory(logDirectory)) throw new IllegalArgumentException("Journal path must be a directory.");
		this.logDirectory = logDirectory;
		this.gson = gson;
	}

	/**
	 * Streams all the journal files found in this journal's directory, ordered from
	 * oldest to newest.
	 * 
	 * @return A stream of {@link JournalFile} instances from this journal's
	 *         directory
	 */
	@SneakyThrows(IOException.class)
	public Stream<JournalFile> journalFiles() {
		return Streams.stream(Files.newDirectoryStream(logDirectory)).filter(Files::isRegularFile)
				.filter(JournalFile::journalPatternMatches).map(f -> new JournalFile(f, gson)).sorted();
	}

	/**
	 * Streams all the events found in this journal's directory, ordered from oldest
	 * to newest.
	 * 
	 * @return A stream of {@link JournalEvent} instances from each journal file in
	 *         this folder.
	 * @see #events(Class)
	 */
	public Stream<JournalEvent> events() {
		return journalFiles().flatMap(JournalFile::events);
	}

	/**
	 * Streams all events of the given type from this journal's directory, ordered
	 * from oldest to newest.
	 * 
	 * @param cls
	 *            The type of events to stream
	 * @return A stream of events of the given type
	 * @see #events()
	 */
	public <T extends JournalEvent> Stream<T> events(Class<T> cls) {
		return events().filter(cls::isInstance).map(cls::cast);
	}

	/**
	 * Searches for the most recent event of a given type
	 * 
	 * @param cls
	 *            The type of event to search for
	 * @return The most recent event of the given type, or an empty
	 *         <code>Optional</code> if no such event could be found
	 */
	public <T extends JournalEvent> Optional<T> lastEventOfType(Class<T> cls) {
		/*
		 * Streams journal files in reverse order (newest files first), then maps the
		 * journal files to the last event of the given type, filters out empty
		 * optionals, and returns the first one (most recent event)
		 */
		return journalFiles().sorted(Comparator.reverseOrder()).map(JournalFile::events)
				.map(s -> s.filter(cls::isInstance).map(cls::cast).reduce((a, b) -> b)).filter(Optional::isPresent)
				.map(Optional::get).findFirst();
	}

	@NonFinal
	private JournalUtils.DirectoryPoker poker;

	private void pokePath(Path p) {
		if (poker == null) {
			poker = new JournalUtils.DirectoryPoker();
			Thread t = new Thread(poker);

			t.setDaemon(true);
			t.setName("EDJournal Directory Poker");
			t.setPriority(Thread.MIN_PRIORITY);

			t.start();
		}

		poker.getPaths().add(p);
	}

	private Stream<WatchEvent<?>> streamWatchEvents() throws IOException {
		return Stream.generate(new Supplier<WatchEvent<?>>() {
			private WatchService w = logDirectory.getFileSystem().newWatchService();
			private WatchKey k = logDirectory.register(w, StandardWatchEventKinds.ENTRY_MODIFY);
			private Iterator<WatchEvent<?>> i = k.pollEvents().iterator();

			{
				pokePath(logDirectory);
			}

			@Override
			@SneakyThrows(InterruptedException.class)
			public WatchEvent<?> get() {
				while (!i.hasNext()) {
					k.reset();
					k = w.take();
					i = k.pollEvents().iterator();
				}

				return i.next();
			}
		});
	}

	@SneakyThrows(IOException.class)
	private Stream<JournalEvent> liveEvents(Instant from) {
		AtomicReference<Instant> cutoff = new AtomicReference<Instant>(Instant.now());

		return streamWatchEvents().filter(e -> e.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY))
				.map(e -> (Path) e.context()).map(logDirectory::resolve).filter(JournalFile::journalPatternMatches)
				.map(p -> new JournalFile(p, gson)).flatMap(f -> f.eventsAfter(cutoff.get()))
				.peek(e -> cutoff.set(e.getTimestamp()));

	}

	/**
	 * Produces an <b>infinite</b> stream of events by watching this journal's
	 * directory for changes. This stream <b>does not</b> contain old events written
	 * to the disk, only events that occurred after the stream was created.<br>
	 * When no events are available, the stream will block until new events occur.
	 * 
	 * @return An infinite, live stream of events as they occur.
	 * @see #liveEvents(Class)
	 * @see #allEvents()
	 * @see #allEvents(Class)
	 */
	public Stream<JournalEvent> liveEvents() {
		return liveEvents(Instant.now());
	}

	/**
	 * Produces an <b>infinite</b> stream of events of a given type by watching this
	 * journal's directory for changes. This stream <b>does not</b> contain old
	 * events written to the disk, only events that occurred after the stream was
	 * created.<br>
	 * When no events are available, the stream will block until new events occur.
	 * 
	 * @param cls
	 *            The type of event to watch for.
	 * @return An infinite, live stream of events of the given type as they occur.
	 * @see #liveEvents()
	 * @see #allEvents()
	 * @see #allEvents(Class)
	 */
	public <T extends JournalEvent> Stream<T> liveEvents(Class<T> cls) {
		return liveEvents().filter(cls::isInstance).map(cls::cast);
	}

	/**
	 * Produces an <b>infinite</b> stream of events using old events stored in the
	 * journal as well as watching for new events being logged. This stream will
	 * first cover every event stored, and then block until more events are written,
	 * never terminating.
	 * 
	 * @return An infinite stream of past and live events.
	 * @see #allEvents(Class)
	 * @see #liveEvents()
	 * @see #liveEvents(Class)
	 */
	public Stream<JournalEvent> allEvents() {
		Instant from = Instant.now();
		return Stream.concat(events(), liveEvents(from));
	}

	/**
	 * Produces an <b>infinite</b> stream of events of a given type using old events
	 * stored in the journal as well as watching for new events being logged. This
	 * stream will first cover every event stored, and then block until more events
	 * are written, never terminating.
	 * 
	 * @param cls
	 *            The type of event to stream.
	 * @return An infinite stream of past and live events of a given type.
	 * @see #allEvents()
	 * @see #liveEvents()
	 * @see #liveEvents(Class)
	 */
	public <T extends JournalEvent> Stream<T> allEvents(Class<T> cls) {
		return allEvents().filter(cls::isInstance).map(cls::cast);
	}
}
