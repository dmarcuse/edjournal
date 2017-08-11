package me.apemanzilla.edjournal;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import me.apemanzilla.edjournal.events.JournalEvent;
import me.apemanzilla.edjournal.events.UnknownJournalEvent;

public class TestJournal {
	public Journal journal;

	@Before
	public void setUp() throws URISyntaxException {
		journal = Journal.create(Paths.get(getClass().getResource("/logs").toURI()));
	}

	@Test
	public void testStreamFiles() {
		assertTrue("No files were streamed", journal.journalFiles().count() > 0);
	}

	@Test
	public void testStreamEvents() {
		assertTrue("No events were streamed", journal.events().count() > 0);
	}

	@Test
	public void testMissingEvents() {
		Optional<JournalEvent> unknown = journal.events().filter(e -> e instanceof UnknownJournalEvent).findFirst();

		unknown.ifPresent(e -> fail("Missing journal event class for " + e));
	}
}
