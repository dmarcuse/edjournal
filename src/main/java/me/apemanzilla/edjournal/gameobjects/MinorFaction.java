package me.apemanzilla.edjournal.gameobjects;

import java.util.List;

import lombok.Data;

@Data
public class MinorFaction {
	@Data
	public static class State {
		private String state;
		private int trend;
	}

	private String name;
	private String factionState;
	private String government;
	private double influence;
	private List<State> pendingStates;
	private List<State> recoveringStates;
}
