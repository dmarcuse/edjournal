package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.BountyReward;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Bounty extends JournalEvent {
	private List<BountyReward> rewards;
	private String victimFaction;
	private long totalReward;
	private int sharedWithOthers = 0;
}
