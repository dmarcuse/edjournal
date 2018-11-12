package me.apemanzilla.edjournal

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds.ENTRY_CREATE
import java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY
import java.util.stream.Stream
import kotlin.streams.asSequence
import kotlin.streams.toList
import kotlin.text.RegexOption.IGNORE_CASE

/**
 * An Elite: Dangerous player journal
 * @param directory The directory to load journal files from
 * @param beta If set, will load beta journal files instead of regular journal files
 * @param unknownEventHandler A callback for when an unrecognized event is encountered. The default value logs a warning
 * using SLF4J.
 */
class Journal(
	val directory: Path,
	beta: Boolean = false,
	unknownEventHandler: (String) -> Unit = { name -> logger.warn("Unrecognized journal event type {}", name) }
) {
	private companion object {
		val logger = logger<Journal>()
	}

	/** The [Gson] instance used for deserializing journal events */
	val gson = GsonBuilder()
		.registerTypeAdapter(ISO8601Adapter)
		.registerTypeAdapter(EventAdapter(unknownEventHandler))
		.create()

	/** A pattern to match journal file names and capture the file number */
	val fileRegex = if (beta) {
		Regex("""^JournalBeta\.(\d+\.\d+)\.log$""", IGNORE_CASE)
	} else {
		Regex("""^Journal\.(\d+\.\d+)\.log$""", IGNORE_CASE)
	}

	/** Stream of the files of this journal, in ascending order (oldest to newest) */
	fun files() = Files.list(directory)
		.filter { Files.isRegularFile(it) && it.fileName.toString().matches(fileRegex) }
		.sorted(Comparator.comparing<Path, String> { fileRegex.matchEntire(it.fileName.toString())!!.groupValues[1] })

	/** Stream all events stored in this file */
	fun Path.events(): Stream<out JournalEvent> = Files.lines(this).map { gson.fromJson<JournalEvent>(it) }

	/** Stream all events stored in this journal*/
	fun events() = files().flatMap { it.events() }

	/**
	 * Create an infinite sequence of journal events, blocking until new events occur. Only events that occur after
	 * calling the method will be captured, including those that occur after the function is called but before the
	 * sequence is iterated.
	 */
	fun liveEvents(): Sequence<JournalEvent> {
		// keep a map of line counts to know how many lines to skip when a change occurs
		val existingEvents = files().asSequence()
			.associate { path -> path.normalize() to Files.lines(path).count() }
			.toMutableMap()

		// create watch service
		val watcher = directory.fileSystem.newWatchService()

		// register directory
		val key = directory.register(watcher, ENTRY_CREATE, ENTRY_MODIFY)

		// create an infinite sequence using the filesystem watcher
		return sequence {
			while (true) {
				// poll for events
				for (watchEvent in key.pollEvents()) {
					// get the affected file
					val file = directory.resolve((watchEvent.context() as Path))

					// check whether it's matched
					if (!(Files.isRegularFile(file) && fileRegex.matches(file.fileName.toString()))) continue

					// get the new events
					val events = Files.lines(file)
						.skip(existingEvents[file] ?: 0)
						.map { gson.fromJson<JournalEvent>(it) }
						.toList()

					// update the line count map
					existingEvents[file] = (existingEvents[file] ?: 0) + events.count()

					// yield the events
					yieldAll(events)
				}

				// reset the key so it may be used again
				key.reset()
			}
		}
	}
}
