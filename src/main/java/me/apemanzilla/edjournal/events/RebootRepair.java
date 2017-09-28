package me.apemanzilla.edjournal.events;

import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class RebootRepair extends JournalEvent {
	List<String> modules = Collections.emptyList();
}
