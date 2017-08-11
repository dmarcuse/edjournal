package me.apemanzilla.edjournal.gameobjects;

import lombok.Data;

@Data
public class PassengerMission {
	private long missionID;
	private String type;
	private boolean VIP;
	private boolean wanted;
	private int count;
}
