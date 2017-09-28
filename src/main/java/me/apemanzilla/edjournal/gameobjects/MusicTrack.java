package me.apemanzilla.edjournal.gameobjects;

import java.lang.reflect.Type;
import java.util.Arrays;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;

@JsonAdapter(MusicTrack.Deserializer.class)
public enum MusicTrack {
	NoTrack, MainMenu, CQCMenu, SystemMap, GalaxyMap, GalacticPowers, CQC, DestinationFromHyperspace,
	DestinationFromSupercruise, Supercruise, Combat_Unknown, Unknown_Encounter, CapitalShip, CombatLargeDogFight,
	Combat_Dogfight, Combat_SRV, Unknown_Settlement, DockingComputer, Starport, Unknown_Exploration, Exploration, Other;

	public static class Deserializer implements JsonDeserializer<MusicTrack> {
		@SuppressWarnings("unlikely-arg-type")
		@Override
		public MusicTrack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			if (Arrays.asList(MusicTrack.values()).contains(json.getAsString())) {
				return MusicTrack.valueOf(json.getAsString());
			} else {
				return MusicTrack.Other;
			}
		}
	}
}
