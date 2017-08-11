package me.apemanzilla.edjournal;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

import me.apemanzilla.edjournal.events.*;
import me.apemanzilla.edjournal.gameobjects.VoucherType;

public class ManualTest {

	public static void main(String[] args) throws URISyntaxException {
		Journal journal = Journal.create(Paths.get(ManualTest.class.getResource("/logs").toURI()));

		System.out
				.println(journal.lastEventOfType(FSDJump.class).map(FSDJump::getStarSystem).orElse("Unknown location"));

		System.out.println(journal.lastEventOfType(Scan.class));

		System.out.println(journal.events(Scan.StarScan.class)
				.filter(s -> s.getTimestamp().isAfter(Instant.now().minus(Duration.ofDays(7)))).count());

		System.out.println(journal.events(RedeemVoucher.class).filter(v -> v.getType() == VoucherType.Bounty)
				.mapToLong(RedeemVoucher::getAmount).sum());
	}

}
