package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.PassengerMission;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Passengers extends JournalEvent {
	private List<PassengerMission> manifest;
}
