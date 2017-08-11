package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.FriendStatus;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Friends extends JournalEvent {
	private String name;
	private FriendStatus status;
}
