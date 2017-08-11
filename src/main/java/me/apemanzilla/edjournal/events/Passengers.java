package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Passenger;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Passengers extends JournalEvent {
	private List<Passenger> manifest;
}
