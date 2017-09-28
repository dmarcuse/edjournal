package me.apemanzilla.edjournal.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class BuyDrones extends JournalEvent {
	private String type;
	private int count;
	private int buyPrice;
	private long totalCost;
}
