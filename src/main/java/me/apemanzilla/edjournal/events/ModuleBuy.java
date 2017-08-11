package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Ship;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ModuleBuy extends JournalEvent {
	private String slot;
	private String buyItem;
	private long buyPrice;
	private Ship ship;
	private int shipID;
	
	@Nullable
	private String storedItem;
	
	@Nullable
	private String sellItem;
	
	@Nullable
	private Long sellPrice;
}
