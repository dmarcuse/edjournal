package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MissionFailed extends JournalEvent {
	private String name;
	private long missionID;
}
