package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class KickCrewMember extends JournalEvent {
	private String crew;
	private boolean onCrime = false;
}
