package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Role;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ChangeCrewRole extends JournalEvent {
	private Role role;
}
