package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ModuleSell extends JournalEvent {
	private String slot;
	private String sellItem;
	private long sellPrice;
	private Ship ship;
	private int shipID;
}
