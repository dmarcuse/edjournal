package me.apemanzilla.edjournal

import com.google.gson.*
import java.lang.reflect.Type

/**
 * An adapter to de/serialize [JournalEvent] instances from/to JSON.
 *
 * @param unknownEventHandler A function to call whenever an unknown event type is encountered during deserialization.
 * The default value will log a warning using SLF4J.
 */
class EventAdapter(val unknownEventHandler: (String) -> Unit) : JsonSerializer<JournalEvent>, JsonDeserializer<JournalEvent> {
	private companion object {
		val logger = logger<EventAdapter>()

		// use a map for faster lookups
		val eventTypeMap by lazy { JournalEvent::class.sealedSubclasses.associate { it.simpleName!! to it.java } }
	}

	override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): JournalEvent? {
		// handle null
		if (json is JsonNull) return null

		// events must be JSON objects
		require(json is JsonObject) { "Expected JSON object, got $json" }

		// get the event type
		val eventType = json["event"].let { type ->
			require((type as? JsonPrimitive)?.isString == true) { "Expected string for event type, got $type" }
			type.asString
		}

		// deserialize the event into the appropriate subclass
		val event = context.deserialize<JournalEvent>(json, eventTypeMap.getOrElse(eventType) {
			unknownEventHandler(eventType)
			return@getOrElse UnknownEvent::class.java
		})

		// store the raw JSON with the event
		event.originalJson = json

		// return the constructed event
		return event
	}

	override fun serialize(src: JournalEvent?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
		// handle null
		src ?: return JsonNull.INSTANCE

		// use original json if possible, else deserialize with gson
		return src.originalJson ?: context.serialize(src)
	}
}
