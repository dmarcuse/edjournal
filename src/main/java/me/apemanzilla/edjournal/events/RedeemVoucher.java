package me.apemanzilla.edjournal.events;

import java.util.Collections;
import java.util.List;

import lombok.*;
import me.apemanzilla.edjournal.gameobjects.VoucherType;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class RedeemVoucher extends JournalEvent {
	@Data
	public static class SubVoucher {
		private String faction;
		private long amount;
	}

	private VoucherType type;
	private long amount;
	private String faction;
	private double brokerPercentage;
	private List<SubVoucher> factions = Collections.emptyList();
}
