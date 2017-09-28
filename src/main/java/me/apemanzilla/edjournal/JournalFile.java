package me.apemanzilla.edjournal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.google.gson.*;

import lombok.*;
import me.apemanzilla.edjournal.events.JournalEvent;

/**
 * Represents a log file as part of the a player's journal.
 * 
 * @author apemanzilla
 * 
 * @see Journal
 * @see JournalEvent
 *
 */
@Value
@ToString(of = "location")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class JournalFile implements Comparable<JournalFile> {
	/**
	 * A regular expression that matches journal file names.
	 * 
	 * @see #journalPatternMatches(Path)
	 */
	public static String journalFilePattern = "^Journal\\.(\\d+)\\.(\\d+)\\.log$";

	/**
	 * Checks whether a given path matches the {@link #journalFilePattern journal
	 * filename pattern}
	 * 
	 * @param path
	 *            The path to check
	 * @return Whether the path's name matches the journal file pattern
	 * @see #journalFilePattern
	 */
	public static boolean journalPatternMatches(Path path) {
		return path.getFileName().toString().matches(journalFilePattern);
	}

	@NonNull
	private final Path location;
	@NonNull
	private transient final Gson gson;

	@Data
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	private static class JsonLine<T extends JsonElement> {
		private static final JsonParser parser = new JsonParser();

		public static JsonLine<? extends JsonElement> parse(Path file, int line, String json) {
			try {
				return new JsonLine<JsonElement>(file, line, parser.parse(json));
			} catch (JsonParseException e) {
				throw new RuntimeException("Failed to parse JSON on line " + line + " of " + file.toAbsolutePath(), e);
			}
		}

		private Path file;
		private int line;
		private T element;
	}

	@SuppressWarnings("unchecked")
	@SneakyThrows(IOException.class)
	private Stream<JsonLine<JsonObject>> streamObjects() {
		AtomicInteger line = new AtomicInteger(0);

		return Files.lines(location).peek(l -> line.incrementAndGet()).map(s -> JsonLine.parse(location, line.get(), s))
				.filter(l -> l.element.isJsonObject()).map(l -> (JsonLine<JsonObject>) l);
	}

	private JournalEvent toEvent(JsonLine<JsonObject> l) {
		try {
			return gson.fromJson(l.element, JournalEvent.class);
		} catch (JsonSyntaxException e) {
			throw new RuntimeException(
					"Failed to parse journal event on line " + l.line + " of " + l.file.toAbsolutePath(), e);
		}
	}

	/**
	 * Streams all the events stored in this journal file. The stream will be
	 * ordered according to the lines in the journal file - i.e. oldest events to
	 * newest events.
	 * 
	 * @return A stream of events from this file
	 */
	public Stream<JournalEvent> events() {
		return streamObjects().map(this::toEvent);
	}

	/**
	 * Streams all events stored in this journal file that occurred after a given
	 * time. The stream will be ordered according to the lines in the journal file -
	 * i.e. oldest events to newest events.
	 * 
	 * @param time
	 *            The cutoff time
	 * @return A stream of events which occurred after the given time
	 */
	public Stream<JournalEvent> eventsAfter(Instant time) {
		return streamObjects()
				.filter(l -> JournalUtils.parseTimestamp(l.element.get("timestamp").getAsString()).isAfter(time))
				.map(this::toEvent);
	}

	private double getTimestampAndPart() {
		Matcher m = Pattern.compile(journalFilePattern).matcher(location.getFileName().toString());

		boolean matched = m.matches();
		assert matched : "Failed to match journal file pattern to file " + location;

		return Double.parseDouble(m.group(1)) + (0.01 * Double.parseDouble(m.group(2)));
	}

	@Override
	public int compareTo(JournalFile o) {
		return Double.compare(getTimestampAndPart(), o.getTimestampAndPart());
	}
}
