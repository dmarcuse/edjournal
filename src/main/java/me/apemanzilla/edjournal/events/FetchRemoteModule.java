package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class FetchRemoteModule extends JournalEvent {
	private String storedItem;
	private int storageSlot;
	private int serverId;
	private long transferCost;
	private Ship ship;
	private int shipID;
}
