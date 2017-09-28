package me.apemanzilla.edjournal.events;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.MinorFaction;
import me.apemanzilla.edjournal.gameobjects.PowerplayState;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class FSDJump extends JournalEvent {
	private String starSystem;
	private double[] starPos;
	private String body;
	private double jumpDist;
	private double fuelUsed;
	private double fuelLevel;
	private int boostUsed;

	@Nullable
	private String systemFaction;
	private String factionState;
	private String systemAllegiance;
	private String systemEconomy;
	private String systemGovernment;
	private String systemSecurity;
	private List<MinorFaction> factions = Collections.emptyList();
	private List<String> powers = Collections.emptyList();

	@Nullable
	private PowerplayState powerplayState;

	@Nullable
	private Long population;
}
