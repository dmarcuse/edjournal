package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ShipyardSell extends JournalEvent {
	private Ship shipType;
	private int sellShipID;
	private long shipPrice;

	@Nullable
	private String system;
}
