package me.apemanzilla.edjournal.events;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.MaterialCategory;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MaterialCollected extends JournalEvent {
	private MaterialCategory category;
	private String name;
	private int count;
}
