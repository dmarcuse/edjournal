package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.ShipScanType;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Scanned extends JournalEvent {
	private ShipScanType scanType;
}
