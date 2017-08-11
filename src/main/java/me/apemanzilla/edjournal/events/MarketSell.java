package me.apemanzilla.edjournal.events;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class MarketSell extends JournalEvent {
	private String type;
	private int count;
	private int sellPrice;
	private long totalSale;
	private int avgPricePaid;
	private boolean illegalGoods = false;
	private boolean stolenGoods = false;
	private boolean blackMarket = false;
}
