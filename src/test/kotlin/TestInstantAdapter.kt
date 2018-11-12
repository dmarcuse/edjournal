package me.apemanzilla.edjournal

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class TestInstantAdapter {
	val gson = GsonBuilder().registerTypeAdapter(ISO8601Adapter).create()

	@Test
	fun `test current time`() {
		val now = Instant.now()
		assertEquals(now, gson.fromJson<Instant>(gson.toJson(now)))
	}

	@Test
	fun `test using timestamp from journal`() {
		val expected = LocalDate.of(2018, 11, 8).atTime(16, 1, 26).toInstant(ZoneOffset.UTC)

		//language=JSON
		assertEquals(expected, gson.fromJson<Instant>(""""2018-11-08T16:01:26Z""""))

		//language=JSON
		assertEquals(""""2018-11-08T16:01:26Z"""", gson.toJson(expected))
	}

	@Test
	fun `test null safety`() {
		//language=JSON
		assertNull(gson.fromJson<Instant>("null"))

		//language=JSON
		assertEquals("null", gson.toJson(null, Instant::class.java))

		assertThrows(JsonSyntaxException::class.java) {
			//language=JSON
			gson.fromJson<Instant>("1")
		}
	}
}
