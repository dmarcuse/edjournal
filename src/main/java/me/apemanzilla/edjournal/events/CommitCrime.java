package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class CommitCrime extends JournalEvent {
	private String crimeType;
	private String faction;

	@Nullable
	private String victim;

	@Nullable
	private Integer fine;

	@Nullable
	private Integer bounty;
}
