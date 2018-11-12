package me.apemanzilla.edjournal

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTimeoutPreemptively
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException
import java.nio.file.*
import java.nio.file.StandardOpenOption.APPEND
import java.nio.file.StandardOpenOption.CREATE
import java.nio.file.attribute.BasicFileAttributes
import java.time.Duration.ofSeconds
import java.time.Instant
import kotlin.streams.toList

class TestJournal {
	lateinit var dir: Path

	@BeforeEach
	fun `create temp dir`() {
		dir = Files.createTempDirectory("edjournal-test")
	}

	@AfterEach
	fun `delete temp dir`() {
		Files.walkFileTree(dir, object : SimpleFileVisitor<Path>() {
			override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
				Files.delete(file)
				return super.visitFile(file, attrs)
			}

			override fun postVisitDirectory(dir: Path, exc: IOException?): FileVisitResult {
				Files.delete(dir)
				return super.postVisitDirectory(dir, exc)
			}
		})
	}

	@Test
	fun `test file matching`() {
		val matched = listOf(
			dir.resolve("Journal.1.1.log"),
			dir.resolve("Journal.2.1.log"),
			dir.resolve("Journal.2.2.log")
		)

		val unmatched = listOf(
			dir.resolve("JournalBeta.1.1.log"),
			dir.resolve("other.txt")
		)

		(matched + unmatched).forEach { Files.createFile(it) }

		val journal = Journal(dir)

		assertEquals(matched, journal.files().toList())
	}

	@Test
	fun `test journal usage`() {
		val journal = Journal(dir)

		val existingEvents = listOf(
			Fileheader(Instant.now(), 1, "EN", "0.0", "0000"),
			Cargo(Instant.now(), listOf())
		)

		Files.write(dir.resolve("Journal.1.1.log"), existingEvents.map { journal.gson.toJson(it) }, CREATE, APPEND)

		assertEquals(journal.events().toList(), existingEvents)

		assertTimeoutPreemptively(ofSeconds(3)) {
			val live = journal.liveEvents()

			val liveEvents = listOf(
				Fileheader(Instant.now(), 2, "EN", "0.0", "0000"),
				Cargo(Instant.now(), listOf(CargoItem(42, 0, "fish", null)))
			)

			Files.write(dir.resolve("Journal.1.1.log"), liveEvents.map { journal.gson.toJson(it) }, CREATE, APPEND)

			assertEquals(live.take(liveEvents.size).toList(), liveEvents)
		}
	}
}
