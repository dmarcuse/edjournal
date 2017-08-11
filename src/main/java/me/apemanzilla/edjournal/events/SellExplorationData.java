package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class SellExplorationData extends JournalEvent {
	private List<String> systems;
	private List<String> discovered;
	private long baseValue;
	private long bonus;
}
