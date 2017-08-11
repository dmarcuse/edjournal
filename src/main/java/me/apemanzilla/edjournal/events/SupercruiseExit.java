package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.BodyType;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class SupercruiseExit extends JournalEvent {
	private String starSystem;
	private String body;
	private BodyType bodyType;
}
