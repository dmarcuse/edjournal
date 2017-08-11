package me.apemanzilla.edjournal.events;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.Killer;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Died extends JournalEvent {
	@Nullable
	private String killerName;

	@Nullable
	private String killerShip;

	@Nullable
	private String killerRank;
	
	private List<Killer> killers = Collections.emptyList();
}
