package me.apemanzilla.edjournal.gameobjects;

import lombok.Data;

@Data
public class Passenger {
	// TODO: confirm these
	private String missionID;
	private String type;
	private boolean VIP;
	private boolean wanted;
	private int count;
}
