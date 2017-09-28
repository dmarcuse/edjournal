package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Material;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Synthesis extends JournalEvent {
	private String name;
	private List<Material> materials;
}
