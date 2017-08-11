package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class BuyExplorationData extends JournalEvent {
	private String system;
	private long cost;
}
