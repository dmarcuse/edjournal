package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class AfmuRepairs extends JournalEvent {
	private String module;
	private boolean fullyRepaired;
	private float health;
}
