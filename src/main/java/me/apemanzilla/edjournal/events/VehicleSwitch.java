package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.VehicleSwitchTarget;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class VehicleSwitch extends JournalEvent {
	private VehicleSwitchTarget to;
}
