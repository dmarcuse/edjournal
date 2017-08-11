# EDJournal [ ![Download](https://api.bintray.com/packages/apemanzilla/maven/edjournal/images/download.svg) ](https://bintray.com/apemanzilla/maven/edjournal/_latestVersion)[![Build Status](https://travis-ci.org/apemanzilla/edjournal.svg?branch=master)](https://travis-ci.org/apemanzilla/edjournal)
A modern library for reading the Elite: Dangerous player journal

EDJournal is an easy to use library for reading the [Elite: Dangerous player journal](http://hosting.zaonce.net/community/journal/v11/Journal_Manual_v11.pdf).

With the power of Java 8 streams, you can do a lot with very little code. Here's a quick example that attempts to get the last star system you jumped into:

```
String currentSystem = Journal.create().lastEventOfType(FSDJump.class)
				.map(FSDJump::getStarSystem)
				.orElse("Unknown location");
```

And here's one that counts how many stars you've scanned in the past week:

```
int lastWeekStars = Journal.create().events(Scan.StarScan.class)
				.filter(s -> s.getTimestamp()
				.isAfter(Instant.now().minus(Duration.ofDays(7))))
				.count());
```

And maybe you're curious about how many credits you've earned from bounty hunting?

```
long bountyHuntingCredits = Journal.create().events(RedeemVoucher.class)
				.filter(v -> v.getType() == VoucherType.Bounty)
				.mapToLong(RedeemVoucher::getAmount).sum()
```

The possibilities are endless!

### Warning
EDJournal is a brand new library and has the ambitious goal of supporting the entire journal specification. It likely has numerous problems that I haven't found yet, so if you have any problems please submit an issue report so I can fix it!