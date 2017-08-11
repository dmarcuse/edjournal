package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Material;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Materials extends JournalEvent {
	private List<Material> raw;
	private List<Material> manufactured;
	private List<Material> encoded;
}
