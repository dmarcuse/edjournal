package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.apemanzilla.edjournal.gameobjects.MusicTrack;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Music extends JournalEvent {
	private MusicTrack musicTrack;
}
