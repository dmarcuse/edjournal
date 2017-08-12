package me.apemanzilla.edjournal;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.*;
import java.time.Instant;
import java.util.TimeZone;

import com.google.gson.*;

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
}
