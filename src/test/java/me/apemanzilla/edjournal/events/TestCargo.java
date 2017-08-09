package me.apemanzilla.edjournal.events;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import me.apemanzilla.edjournal.Journal;
import me.apemanzilla.edjournal.events.Cargo.CargoItem;

public class TestCargo {
	public String emptyCargoJson = "{ \"timestamp\":\"2017-08-08T22:30:34Z\", \"event\":\"Cargo\", \"Inventory\":[  ] }";
	public String dronesCargoJson = "{ \"timestamp\":\"2017-04-12T21:03:23Z\", \"event\":\"Cargo\", \"Inventory\":[ { \"Name\":\"drones\", \"Count\":8 } ] }";
	
	public Cargo emptyCargo = new Cargo();
	public Cargo dronesCargo = new Cargo();
	
	@Before
	public void setup() throws ParseException {
		emptyCargo.timestamp = Journal.timestampFormat.parse("2017-08-08T22:30:34Z").toInstant();
		emptyCargo.inventory = new ArrayList<>();
		
		dronesCargo.timestamp = Journal.timestampFormat.parse("2017-04-12T21:03:23Z").toInstant();
		dronesCargo.inventory = new ArrayList<>();
		dronesCargo.inventory.add(new CargoItem("drones", 8));
	}
	
	@Test
	public void testEmpty() throws ParseException {
		assertEquals(emptyCargo, Journal.gson().fromJson(emptyCargoJson, Cargo.class));
	}
	
	@Test
	public void testDrones() throws ParseException {
		assertEquals(dronesCargo, Journal.gson().fromJson(dronesCargoJson, Cargo.class));
	}
}
