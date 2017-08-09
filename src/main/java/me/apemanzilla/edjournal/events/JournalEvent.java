package me.apemanzilla.edjournal.events;

import java.time.Instant;

import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public abstract class JournalEvent implements Comparable<JournalEvent> {
	public JournalEvent() {
		event = this.getClass().getSimpleName();
	}
	
	/**
	 * The time at which the event was written to the journal
	 */
	@SerializedName("timestamp")
	Instant timestamp;

	/**
	 * The name of the event
	 */
	@SerializedName("event")
	String event;

	@Override
	public final int compareTo(JournalEvent other) {
		return getTimestamp().compareTo(other.getTimestamp());
	}
}
