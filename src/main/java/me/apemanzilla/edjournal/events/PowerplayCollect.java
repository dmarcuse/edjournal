package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class PowerplayCollect extends JournalEvent {
	private String power;
	private String type;
	private int count;
}
