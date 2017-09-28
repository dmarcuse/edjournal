package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ShipyardBuy extends JournalEvent {
	private Ship shipType;
	private long shipPrice;

	@Nullable
	private Ship storeOldShip;

	@Nullable
	private int storeShipID;

	@Nullable
	private Ship sellOldShip;

	@Nullable
	private int sellShipID;

	@Nullable
	private long sellPrice;
}
