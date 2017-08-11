package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class CommunityGoalReward extends JournalEvent {
	private String name;
	private String system;
	private long reward;
}
