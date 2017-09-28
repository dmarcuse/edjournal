package me.apemanzilla.edjournal.events;

import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class NewCommander extends JournalEvent {
	private String name;

	@SerializedName("package")
	private String starterPackage;
}
