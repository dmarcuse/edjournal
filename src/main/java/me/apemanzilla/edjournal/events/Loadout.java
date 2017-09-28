package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Ship;
import me.apemanzilla.edjournal.gameobjects.ShipModule;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Loadout extends JournalEvent {
	private Ship ship;
	private int shipID;
	private String shipName;
	private String shipIdent;
	private List<ShipModule> modules;
}
