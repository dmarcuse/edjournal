package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class CommunityGoalJoin extends JournalEvent {
	private String name;
	private String system;
}
