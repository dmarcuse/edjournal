package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Screenshot extends JournalEvent {
	private String filename;
	private int width;
	private int height;
	private String system;
	private String body;

	@Nullable
	private Double latitude, longitude;
}
