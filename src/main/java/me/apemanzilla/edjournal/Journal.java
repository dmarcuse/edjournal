package me.apemanzilla.edjournal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.google.common.collect.Streams;
import com.google.gson.Gson;

import lombok.*;
import me.apemanzilla.edjournal.events.JournalEvent;

@Value
public class Journal {
	/**
	 * Creates a new journal that will load logs from the given journal
	 * directory.
	 * 
	 * @param logDirectory
	 *            The directory to watch for player journal files
	 * @return A new <code>Journal</code> instance
	 */
	public static Journal create(Path logDirectory) {
		return new Journal(logDirectory, JournalUtils.gson);
	}

	/**
	 * Creates a journal from the
	 * {@link JournalUtils#getDefaultJournalDirectory() default Elite: Dangerous
	 * player journal directory}.
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
	 * Streams all the journal files found in this journal's directory, ordered
	 * from oldest to newest.
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
	 * Streams all the events found in this journal's directory, ordered from
	 * oldest to newest.
	 * 
	 * @return A stream of {@link JournalEvent} instances from each journal file
	 *         in this folder.
	 */
	public Stream<JournalEvent> events() {
		return journalFiles().flatMap(JournalFile::events);
	}
}
