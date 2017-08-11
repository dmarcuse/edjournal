package me.apemanzilla.edjournal.events;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Location extends JournalEvent {
	private String starSystem;
	private double[] starPos;
	private String body;
	private BodyType bodyType;
	private boolean docked;

	@Nullable
	private Double latitude;

	@Nullable
	private Double longitude;

	@Nullable
	private String stationName;

	@Nullable
	private String stationType;

	private String systemFaction;
	private String factionState;
	private String systemAllegiance;
	private String systemEconomy;
	private String systemGovernment;
	private String systemSecurity;
	private List<MinorFaction> factions = Collections.emptyList();
	private List<String> powers;

	@Nullable
	private PowerplayState powerplayState;
}
