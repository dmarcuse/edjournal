package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.MaterialCategory;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ScientificResearch extends JournalEvent {
	private String name;
	private MaterialCategory category;
	private int count;
}
