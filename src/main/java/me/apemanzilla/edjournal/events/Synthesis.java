package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Material;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Synthesis extends JournalEvent {
	private String name;
	private List<Material> materials;
}
