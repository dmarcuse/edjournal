package me.apemanzilla.edjournal.events;

import com.google.gson.annotations.SerializedName;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Fileheader extends JournalEvent {
	@SerializedName("part")
	private int part;

	@SerializedName("language")
	private String language;

	@SerializedName("gameversion")
	private String gameversion;

	@SerializedName("build")
	private String build;
}
