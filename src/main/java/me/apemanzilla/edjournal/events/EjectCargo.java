package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class EjectCargo extends JournalEvent {
	private String type;
	private int count;
	private boolean abandoned;

	@Nullable
	private String powerplayOrigin;
}
