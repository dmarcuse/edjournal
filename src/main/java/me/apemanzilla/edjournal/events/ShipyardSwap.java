package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ShipyardSwap extends JournalEvent {
	private Ship shipType;
	private int shipID;

	@Nullable
	private Ship storeOldShip;

	@Nullable
	private int storeShipID;

	@Nullable
	private Ship sellOldShip;

	@Nullable
	private int sellShipID;
}
