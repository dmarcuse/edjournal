package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class PowerplayDefect extends JournalEvent {
	private String fromPower;
	private String toPower;
}
