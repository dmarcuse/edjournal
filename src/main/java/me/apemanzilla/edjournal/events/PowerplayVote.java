package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class PowerplayVote extends JournalEvent {
	public String power;
	public int votes;
	public String system;
}
