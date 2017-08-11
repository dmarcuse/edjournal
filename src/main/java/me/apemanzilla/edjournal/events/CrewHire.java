package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class CrewHire extends JournalEvent {
	private String name;
	private String faction;
	private long cost;
	private int combatRank;
}
