package me.apemanzilla.edjournal;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Journal {
	public static final Path journalDirectory = Paths.get(System.getProperty("user.home"), "Saved Games",
			"Frontier Developments", "Elite Dangerous");
	
	public static final DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	static {
		timestampFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	private static Gson gson;

	public static Gson gson() {
		if (gson != null)
			return gson;

		GsonBuilder builder = new GsonBuilder();

		// de/serialize timestamps as ISO 8601 in GMT
		builder.registerTypeAdapter(new TypeToken<Instant>() {}.getType(), new TypeAdapter<Instant>() {
			@Override
			public void write(JsonWriter out, Instant value) throws IOException {
				out.value(timestampFormat.format(Date.from(value)));
			}

			@Override
			public Instant read(JsonReader in) throws IOException {
				try {
					return timestampFormat.parse(in.nextString()).toInstant();
				} catch (ParseException e) {
					throw new IOException("Failed to parse timestamp", e);
				}
			}
		});
		
		// most names in the journal are UpperCamelCase rather than lowerCamelCase
		builder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);

		gson = builder.create();
		return gson;
	}
}
