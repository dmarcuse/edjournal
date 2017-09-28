package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
