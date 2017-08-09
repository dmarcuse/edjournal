package me.apemanzilla.edjournal.events;

import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Getter
public class Fileheader extends JournalEvent {
	@SerializedName("part")
	int part;
	
	@SerializedName("language")
	String language;
	
	@SerializedName("gameversion")
	String gameversion;
	
	@SerializedName("build")
	String build;
}
