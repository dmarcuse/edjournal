package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class DatalinkVoucher extends JournalEvent {
	private long reward;
	private String victimFaction;
	private String payeeFaction;
}
