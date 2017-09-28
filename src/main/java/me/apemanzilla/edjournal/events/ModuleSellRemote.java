package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ModuleSellRemote extends JournalEvent {
	private String storageSlot;
	private String sellItem;
	private int serverId;
	private long sellPrice;
	private Ship ship;
	private int shipID;
}
