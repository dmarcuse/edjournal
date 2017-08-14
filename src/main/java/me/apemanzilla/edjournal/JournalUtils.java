package me.apemanzilla.edjournal;

import java.io.IOException;
import java.nio.file.*;
import java.text.*;
import java.time.Instant;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.*;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import me.apemanzilla.edjournal.events.JournalEvent;
import me.apemanzilla.edjournal.events.Scan;

@UtilityClass
public class JournalUtils {
	public static final DateFormat timestampFormat;

	@SneakyThrows(ParseException.class)
	public static Instant parseTimestamp(String timestamp) {
		return timestampFormat.parse(timestamp).toInstant();
	}

	static final Gson gson;

	static {
		timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		timestampFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		JsonDeserializer<Instant> instantDeserializer = (e, t, c) -> parseTimestamp(e.getAsString());

		gson = new GsonBuilder().registerTypeAdapter(Instant.class, instantDeserializer)
				.registerTypeAdapterFactory(new LegacyJournalHandler())
				.registerTypeAdapter(JournalEvent.class, new JournalEventDeserializer())
				.registerTypeAdapter(Scan.class, new ScanDeserializer())
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
	}

	/**
	 * Gets the default Elite: Dangerous player journal directory on this
	 * system.<br>
	 * This will be
	 * <code>C:/Users/[username]/Saved Games/Frontier Developments/Elite Dangerous</code>
	 * on Windows, and
	 * <code>~/Library/Application Support/Frontier Developments/Elite Dangerous</code>
	 * on Mac.
	 * 
	 * @see Journal#create()
	 * 
	 * @return The default journal directory
	 * 
	 * @throws IllegalStateException
	 *             Thrown when the default player journal directory cannot be
	 *             determined (e.g. on Linux)
	 */
	public static Path getDefaultJournalDirectory() {
		String os = System.getProperty("os.name").toLowerCase();

		if (os.startsWith("win"))
			return Paths.get(System.getProperty("user.home"), "Saved Games", "Frontier Developments",
					"Elite Dangerous");
		else if (os.startsWith("mac"))
			return Paths.get("~", "Library", "Application Support", "Frontier Developments", "Elite Dangerous");
		else
			throw new IllegalStateException("Cannot determine default player journal directory");
	}

	/**
	 * <code>WatchEvents</code> don't show up unless you "poke" the files
	 * 
	 * @author apemanzilla
	 *
	 */
	static class DirectoryPoker implements Runnable {
		@Getter
		public final Set<Path> paths = ConcurrentHashMap.newKeySet();

		@Override
		public void run() {
			while (true) {
				try {
					for (Path p : paths) {
						Files.list(p).forEach(t -> {
							try {
								Files.size(t);
							} catch (IOException e) {
								e.printStackTrace();
							}
						});
					}

					Thread.sleep(100);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
