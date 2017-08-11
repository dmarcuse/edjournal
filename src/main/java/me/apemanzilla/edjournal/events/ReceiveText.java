package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.ChatChannel;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ReceiveText extends JournalEvent {
	private String from;
	private String message;
	private ChatChannel channel;
}
