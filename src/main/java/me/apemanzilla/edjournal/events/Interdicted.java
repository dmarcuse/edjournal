package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Interdicted extends JournalEvent {
	private boolean submitted;
	private String interdictor;
	private boolean isPlayer;

	@Nullable
	private Integer combatRank;

	@Nullable
	private String faction;

	@Nullable
	private String power;
}
