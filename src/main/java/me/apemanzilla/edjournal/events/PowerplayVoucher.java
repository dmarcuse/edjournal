package me.apemanzilla.edjournal.events;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class PowerplayVoucher extends JournalEvent {
	private String power;
	private List<String> system;
}
