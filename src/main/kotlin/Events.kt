@file:Suppress("unused")

package me.apemanzilla.edjournal

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.time.Instant

/** An event logged in an Elite: Dangerous player journal */
sealed class JournalEvent {
	/**
	 * The original JSON this event was deserialized from, set when using [EventAdapter]. Constructed events will have a
	 * `null` value here. Using [EventAdapter] to re-serialize the event is preferred: it will use this value if set, or
	 * otherwise serialize the object from individual members.
	 */
	@Transient
	var originalJson: JsonObject? = null
		get() = field?.deepCopy()
		internal set(value) {
			field = value?.deepCopy()
		}

	/** The type of this event */
	// todo: will this affect performance negatively?
	val event = javaClass.simpleName!!

	/** The time this event was logged */
	abstract val timestamp: Instant
}

/** An unknown journal event - may occur due to corrupted journal or mismatched library/journal */
data class UnknownEvent(override val timestamp: Instant) : JournalEvent()

/** The header for a journal file */
data class Fileheader(
	override val timestamp: Instant,
	val part: Int,
	val language: String,
	val gameversion: String,
	val build: String
) : JournalEvent()

data class Cargo(
	override val timestamp: Instant,
	@SerializedName("Inventory") val inventory: List<CargoItem>
) : JournalEvent()

data class ClearSavedGame(override val timestamp: Instant, @SerializedName("Name") val name: String) : JournalEvent()

data class Commander(
	override val timestamp: Instant,
	@SerializedName("Name") val name: String,
	@SerializedName("FID") val fid: String
) : JournalEvent()
