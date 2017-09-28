package me.apemanzilla.edjournal.events;

import java.time.Instant;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class CommunityGoal extends JournalEvent {
	private long CGID;
	private String title;
	private String systemName;
	private String marketName;
	private Instant expiry;
	private boolean isComplete;
	private long currentTotal;
	private int playerContribution;
	private int numContributors;
	private double playerPercentileBand;

	@Nullable
	private String tierReached;
	@Nullable
	private Long bonus;

	@Nullable
	private int topRankSize;
	@Nullable
	private boolean playerInTopRank;
}
