package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class PVPKill extends JournalEvent {
	private String victim;
	private int combatRank;
}
