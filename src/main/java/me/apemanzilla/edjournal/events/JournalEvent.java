package me.apemanzilla.edjournal.events;

import java.time.Instant;

import com.google.gson.annotations.SerializedName;

import lombok.*;

/**
 * Represents an event from the Elite: Dangerous player journal.<br>
 * This class alone serves merely as a base class for all other event classes in
 * {@link me.apemanzilla.edjournal.events}.
 * 
 * @author apemanzilla
 *
 */
@EqualsAndHashCode
@ToString
@Getter
public abstract class JournalEvent {
	/**
	 * The time at which the event occurred
	 */
	@SerializedName("timestamp")
	private Instant timestamp;

	/**
	 * The name of the event
	 */
	@SerializedName("event")
	private String event;

	public JournalEvent(String eventName) {
		event = eventName;
	}

	public JournalEvent() {
		event = getClass().getSimpleName();
	}
}
