package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class FuelScoop extends JournalEvent {
	private double scooped;
	private double total;
}
