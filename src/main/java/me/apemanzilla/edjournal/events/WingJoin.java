package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class WingJoin extends JournalEvent {
	private List<String> others;
}
