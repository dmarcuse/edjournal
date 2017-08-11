package me.apemanzilla.edjournal.gameobjects;

import javax.annotation.Nullable;

import lombok.Data;

@Data
public class ShipModule {
	private String slot;
	private String item;
	private boolean on;
	private int priority;
	private double health;
	private int value;

	@Nullable
	private Integer ammoInClip;
	@Nullable
	private Integer ammoInHopper;
	@Nullable
	private String engineerBlueprint;
	@Nullable
	private String engineerLevel;
}
