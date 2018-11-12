package me.apemanzilla.edjournal

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestGsonExtensions {
	data class IntHolder(val value: Int)

	val gson = Gson()

	@Test
	fun `test basic deserialization`() {
		//language=JSON
		assertEquals(
			IntHolder(5),
			gson.fromJson<IntHolder>("""{"value": 5}""")
		)
	}

	@Test
	fun `test generic deserialization`() {
		//language=JSON
		assertEquals(
			listOf(IntHolder(1), IntHolder(2), IntHolder(3)),
			gson.fromJson<List<IntHolder>>("""[{"value":1},{"value":2},{"value":3}]""")
		)
	}
}
