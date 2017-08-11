package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class SetUserShipName extends JournalEvent {
	private Ship ship;
	private int shipID;
	private String userShipName;
	private String userShipId;
}
