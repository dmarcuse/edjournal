package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Resurrect extends JournalEvent {
	private String option;
	private long cost;
	private boolean bankrupt = false;
}
