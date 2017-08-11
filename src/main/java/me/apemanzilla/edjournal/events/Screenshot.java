package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Screenshot extends JournalEvent {
	private String filename;
	private int width;
	private int height;
	private String system;
	private String body;
}
