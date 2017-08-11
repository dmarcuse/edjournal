package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class EscapeInterdiction extends JournalEvent {
	private String interdictor;
	private boolean isPlayer;
}
