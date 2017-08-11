package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class CollectCargo extends JournalEvent {
	private String type;
	private boolean stolen;
}
