package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.MaterialCategory;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MaterialDiscarded extends JournalEvent {
	private MaterialCategory category;
	private String name;
	private int count;
}
