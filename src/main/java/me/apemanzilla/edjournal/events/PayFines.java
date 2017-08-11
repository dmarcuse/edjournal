package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class PayFines extends JournalEvent {
	private long amount;
	private double brokerPercentage = 0;
}
