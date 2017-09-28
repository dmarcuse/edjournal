package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ModuleSwap extends JournalEvent {
	private String fromSlot;
	private String toSlot;
	private String fromItem;
	private String toItem;
	private Ship ship;
	private int shipID;
}
