package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class WingJoin extends JournalEvent {
	private List<String> others;
}
