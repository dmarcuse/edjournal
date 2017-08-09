package me.apemanzilla.edjournal;

import java.nio.file.Path;

import lombok.Value;

@Value
public class JournalFile {
	private Path source;
}
