package me.apemanzilla.edjournal.events;

import com.google.gson.annotations.SerializedName;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class USSDrop extends JournalEvent {
	@SerializedName("USSType")
	private String ussType;
	
	@SerializedName("USSThreat")
	private int ussThreat; 
}
