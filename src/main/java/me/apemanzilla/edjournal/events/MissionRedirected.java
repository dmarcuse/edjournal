package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MissionRedirected extends JournalEvent {
	private String missionName;
	private long missionID;
	private String newDestinationStation;
	private String oldDestinationStation;
	private String newDestinationSystem;
	private String oldDestinationSystem;
}
