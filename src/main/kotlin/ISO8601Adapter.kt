package me.apemanzilla.edjournal

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.Instant
import java.time.format.DateTimeFormatter.ISO_INSTANT

/** A [TypeAdapter] for [Instant] that parses/formats using ISO 8601 format */
object ISO8601Adapter : TypeAdapter<Instant?>() {
	override fun write(writer: JsonWriter, value: Instant?) {
		when (value) {
			null -> writer.nullValue()
			else -> writer.value(ISO_INSTANT.format(value))
		}
	}

	override fun read(reader: JsonReader): Instant? {
		return when (reader.peek()) {
			JsonToken.NULL -> null
			JsonToken.STRING -> Instant.from(ISO_INSTANT.parse(reader.nextString()))
			else -> throw IllegalStateException("Unexpected JSON token: ${reader.peek()}")
		}
	}
}
