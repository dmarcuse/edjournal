package me.apemanzilla.edjournal;

import static java.nio.file.Paths.get;
import static me.apemanzilla.edjournal.JournalFile.journalPatternMatches;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestJournalFile {
	@Test
	public void testJournalPatternMatches() {
		// malformed prefixes/suffixes
		assertFalse(journalPatternMatches(get("Journal.1.1.logs")));
		assertFalse(journalPatternMatches(get("Journal.1.1.LOG")));
		assertFalse(journalPatternMatches(get("journal.1.1.log")));
		assertFalse(journalPatternMatches(get("MyJournal.1.1.log")));
		assertFalse(journalPatternMatches(get("Journal.1.1.log.gz")));

		// malformed timestamps/parts
		assertFalse(journalPatternMatches(get("Journal.1.log")));
		assertFalse(journalPatternMatches(get("Journal.1.a.log")));
		assertFalse(journalPatternMatches(get("Journal.-1.1.log")));
		assertFalse(journalPatternMatches(get("Journal...log")));
		assertFalse(journalPatternMatches(get("Journal.a.a.log")));

		// correctly formed names
		assertTrue(journalPatternMatches(get("Journal.0.0.log")));
		assertTrue(journalPatternMatches(get("Journal.1.0.log")));
		assertTrue(journalPatternMatches(get("Journal.1230.01.log")));
	}
}
