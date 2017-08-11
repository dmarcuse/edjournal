package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Promotion extends JournalEvent {
	@Nullable
	private int combat, trade, explore, federation, empire;

	@Nullable
	@SerializedName("CQC")
	private int cqc;
}
