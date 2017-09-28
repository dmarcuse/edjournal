package me.apemanzilla.edjournal.events;

import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Progress extends JournalEvent {
	private int combat;
	private int trade;
	private int explore;
	private int empire;
	private int federation;

	@SerializedName("CQC")
	private int cqc;
}
