package me.apemanzilla.edjournal

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class TestEventAdapter {
	val gson = GsonBuilder()
		.registerTypeAdapter(ISO8601Adapter)
		.registerTypeAdapter(EventAdapter {})
		.create()

	//language=JSON
	val fileheaderJson = """{
					"timestamp":"2018-11-08T16:01:26Z",
					"event":"Fileheader", "part":1,
					"language":"English\\UK",
					"gameversion":"3.3.0.200 EDH (Beta 2)",
					"build":"r187310/r0 "
				}""".trimIndent()

	val fileheaderEvent = Fileheader(
		timestamp = LocalDate.of(2018, 11, 8).atTime(16, 1, 26).toInstant(ZoneOffset.UTC), part = 1,
		language = "English\\UK", gameversion = "3.3.0.200 EDH (Beta 2)", build = "r187310/r0 "
	)

	@Test
	fun `test basic deserialization`() {
		assertEquals(
			fileheaderEvent,
			gson.fromJson<JournalEvent>(fileheaderJson)
		)
	}

	@Test
	fun `test reserialization`() {
		val original = gson.fromJson<JsonObject>(fileheaderJson)

		assertEquals(
			original,
			gson.toJsonTree(gson.fromJson<JournalEvent>(original))
		)
	}

	@Test
	fun `test serializing without original json`() {
		val original = gson.fromJson<JsonObject>(fileheaderJson)

		val event = gson.fromJson<JournalEvent>(original)
		event.originalJson = null

		assertEquals(
			original,
			gson.toJsonTree(event)
		)
	}

	@Test
	fun `test unknown event deserialization`() {
		assertEquals(
			UnknownEvent(LocalDate.of(2018, 11, 8).atTime(16, 1, 26).toInstant(ZoneOffset.UTC)),
			//language=JSON
			gson.fromJson<JournalEvent>("""{"timestamp":"2018-11-08T16:01:26Z","event":"fake"}""")
		)
	}

	@Test
	fun `test null deserialization`() {
		assertEquals(
			null,
			//language=JSON
			gson.fromJson<JournalEvent>("null")
		)
	}
}
