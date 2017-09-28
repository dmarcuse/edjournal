package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.ShipScanType;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Scanned extends JournalEvent {
	private ShipScanType scanType;
}
