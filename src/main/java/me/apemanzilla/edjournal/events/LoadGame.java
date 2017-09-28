package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.GameMode;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class LoadGame extends JournalEvent {
	private String commander;
	private Ship ship;
	private int shipID;
	private boolean startLanded = false;
	private boolean startDead = false;
	private GameMode gameMode;
	private String group;
	private long credits;
	private long loan;
	private String shipName;
	private String shipIdent;
	private double fuelLevel;
	private double fuelCapacity;
}
