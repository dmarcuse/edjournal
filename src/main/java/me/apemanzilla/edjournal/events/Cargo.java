package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
public class Cargo extends JournalEvent {
	@Data
	@AllArgsConstructor
	public static class CargoItem {
		String name;
		int count;
	}
	
	List<CargoItem> inventory;
}
