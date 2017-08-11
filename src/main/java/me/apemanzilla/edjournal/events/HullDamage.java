package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class HullDamage extends JournalEvent {
	private double health;
	private boolean playerPilot;
	private boolean figher = false;
}
