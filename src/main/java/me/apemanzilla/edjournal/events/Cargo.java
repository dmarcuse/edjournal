package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.CargoItem;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Cargo extends JournalEvent {
	private List<CargoItem> inventory;
}
