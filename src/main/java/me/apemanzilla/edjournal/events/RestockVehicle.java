package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class RestockVehicle extends JournalEvent {
	private String type;
	private String loadout;
	private long cost;
	private int count;
}
