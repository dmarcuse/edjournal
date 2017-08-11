package me.apemanzilla.edjournal.gameobjects;

import lombok.Getter;

public enum Ship { //@formatter:off
	SideWinder("Sidewinder"),
	Eagle,
	Hauler,
	Adder,
	Viper("Viper MkIII"),
	CobraMkIII("Cobra MkIII"),
	Type6("Type-6 Transporter"),
	Dolphin,
	Type7("Type-7 Transporter"),
	Asp("Asp Explorer"),
	Vulture,
	Empire_Trader("Imperial Clipper"),
	Federation_Dropship("Federal Dropship"),
	Orca,
	Type9("Type-9 Heavy"),
	Python,
	BelugeLiner("Beluge Liner"),
	FerDeLance("Fer-de-Lance"),
	Anaconda,
	Federation_Corvette("Federal Corvette"),
	Cutter("Imperial Cutter"),
	DiamondBack("Diamondback Scout"),
	Empire_Courier("Imperial Courier"),
	DiamondBackXL("Diamondback Explorer"),
	Empire_Eagle("Imperial Eagle"),
	Federation_Dropship_MkII("Federal Assault Ship"),
	Federation_Gunship("Federal Gunship"),
	Viper_MkIV("Viper MkIV"),
	CobraMkIV("Cobra MkIV"),
	Independant_Trader("Keelback"),
	Asp_Scout("Asp Scout");
	//@formatter:on

	@Getter
	private final String humanName;

	private Ship(String name) {
		humanName = name;
	}

	private Ship() {
		humanName = name();
	}

	@Override
	public String toString() {
		return humanName;
	}
}
