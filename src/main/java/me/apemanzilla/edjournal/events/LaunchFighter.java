package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class LaunchFighter extends JournalEvent {
	private String loadout;
	private boolean playerControlled;
}
