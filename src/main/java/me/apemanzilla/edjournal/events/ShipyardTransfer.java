package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ShipyardTransfer extends JournalEvent {
	private Ship shipType;
	private int shipID;
	private String system;
	private double distance;
	private long transferPrice;
}
