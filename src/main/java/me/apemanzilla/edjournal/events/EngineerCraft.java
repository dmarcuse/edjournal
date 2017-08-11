package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Material;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class EngineerCraft extends JournalEvent {
	private String engineer;
	private String blueprint;
	private int level;
	private List<Material> ingredients;
}
