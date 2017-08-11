package me.apemanzilla.edjournal;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import me.apemanzilla.edjournal.events.JournalEvent;

public class ManualTest {

	public static void main(String[] args) throws URISyntaxException {
		Journal journal = Journal.create(Paths.get(ManualTest.class.getResource("/logs").toURI()));
		
		List<JournalEvent> events = journal.events().collect(Collectors.toList());
		
		System.out.println(events);
	}

}
