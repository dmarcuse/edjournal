package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class SellShipOnRebuy extends JournalEvent {
	private Ship shipType;
	private String system;
	private int sellShipId;
	private long shipPrice;
}
