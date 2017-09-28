package me.apemanzilla.edjournal.events;

import java.time.Instant;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.Influence;
import me.apemanzilla.edjournal.gameobjects.Reputation;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MissionAccepted extends JournalEvent {
	private String mission;
	private String faction;
	private long missionID;
	private Influence influence;
	private Reputation reputation;

	@Nullable
	private String commodity;

	@Nullable
	private Integer count;

	@Nullable
	private String target;

	@Nullable
	private String targetType;

	@Nullable
	private String targetFaction;

	@Nullable
	private Integer killCount;

	@Nullable
	private Instant expiry;

	@Nullable
	private String destinationSystem;

	@Nullable
	private String destinationStation;

	@Nullable
	private Integer passengerCount;

	@Nullable
	private Boolean passengerVIPs;

	@Nullable
	private Boolean passengerWanted;

	@Nullable
	private String passengerType;
}
