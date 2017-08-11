package me.apemanzilla.edjournal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.google.gson.*;

import lombok.*;
import me.apemanzilla.edjournal.events.JournalEvent;

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class JournalFile implements Comparable<JournalFile> {
	/**
	 * A regular expression that matches journal file names.
	 * 
	 * @see #journalPatternMatches(Path)
	 */
	public static String journalFilePattern = "^Journal\\.(\\d+)\\.(\\d+)\\.log$";

	/**
	 * Checks whether a given path matches the {@link #journalFilePattern
	 * journal filename pattern}
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
	private final Gson gson;

	/**
	 * Streams all the events stored in this journal file. The stream will be
	 * ordered according to the lines in the journal file - i.e. oldest events
	 * to newest events.
	 * 
	 * @return A stream of events from this file
	 */
	@SneakyThrows(IOException.class)
	public Stream<JournalEvent> events() {
		JsonParser parser = new JsonParser();
		AtomicInteger line = new AtomicInteger(0);
		return Files.lines(location).peek(l -> line.incrementAndGet()).map(l -> {
			try {
				return parser.parse(l);
			} catch (JsonParseException e) {
				throw new RuntimeException("Failed to parse JSON on line " + line.get() + " of " + location.toString(),
						e);
			}
		}).map(JsonElement::getAsJsonObject).map(e -> {
			try {
				return gson.fromJson(e, JournalEvent.class);
			} catch (JsonSyntaxException ex) {
				throw new RuntimeException("Failed to parse JSON on line " + line.get() + " of " + location.toString(),
						ex);
			}
		});
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
