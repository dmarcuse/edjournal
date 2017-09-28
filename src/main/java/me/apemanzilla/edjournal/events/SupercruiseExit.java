package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.BodyType;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class SupercruiseExit extends JournalEvent {
	private String starSystem;
	private String body;
	private BodyType bodyType;
}
