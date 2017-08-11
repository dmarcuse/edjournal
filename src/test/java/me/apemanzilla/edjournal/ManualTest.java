package me.apemanzilla.edjournal;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import me.apemanzilla.edjournal.events.Bounty;
import me.apemanzilla.edjournal.events.RedeemVoucher;
import me.apemanzilla.edjournal.gameobjects.VoucherType;

public class ManualTest {
	public static void main(String args[]) throws URISyntaxException {
		Journal journal = Journal.create(Paths.get(ManualTest.class.getResource("/logs").toURI()));

		long highestPayout = journal.events(RedeemVoucher.class).filter(v -> v.getType() == VoucherType.Bounty)
				.mapToLong(RedeemVoucher::getAmount).sorted().reduce((a, b) -> b).getAsLong();

		highestPayout = journal.events(Bounty.class).mapToLong(Bounty::getTotalReward).reduce((a, b) -> b).getAsLong();

		System.out.println(highestPayout);
	}
}
