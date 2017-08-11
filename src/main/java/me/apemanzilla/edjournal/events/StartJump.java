package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.JumpType;
import me.apemanzilla.edjournal.gameobjects.StarClass;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class StartJump extends JournalEvent {
	private JumpType jumpType;
	private String starSystem;
	
	@Nullable
	private StarClass starClass;
}
