package me.apemanzilla.edjournal.events;

import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class EngineerContribution extends JournalEvent {
	private String engineer;
	private String type;

	@Nullable
	private String commodity;

	@Nullable
	private String material;

	@Nullable
	private String faction;
	private int quantity;
	private int totalQuantity;
}
