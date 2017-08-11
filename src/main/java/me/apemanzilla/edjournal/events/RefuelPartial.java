package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class RefuelPartial extends JournalEvent {
	private long cost;
	private double amount;
}
