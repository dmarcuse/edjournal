package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class PowerplayJoin extends JournalEvent {
	private String power;
}
