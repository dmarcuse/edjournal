package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class EndCrewSession extends JournalEvent {
	private boolean onCrime = false;
}
