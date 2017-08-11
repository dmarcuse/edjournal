package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Liftoff extends JournalEvent {
	@Nullable
	private Double latitude;

	@Nullable
	private Double longitude;
	private boolean playerControlled;
}
