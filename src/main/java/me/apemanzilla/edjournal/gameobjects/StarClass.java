package me.apemanzilla.edjournal.gameobjects;

import lombok.Getter;

import static me.apemanzilla.edjournal.gameobjects.StarClass.Category.*;

/**
 * Represents a specific star class. Classes are also split into broader
 * categories defined in {@link Category}.
 * 
 * @author apemanzilla
 *
 */
public enum StarClass {
	/* main sequence stars */
	O(MainSequence), B(MainSequence), A(MainSequence), F(MainSequence), G(MainSequence), K(MainSequence),
	M(MainSequence), L(MainSequence), T(MainSequence), Y(MainSequence),

	/* proto stars */
	TTS(Proto), AeBe(Proto),

	/* wolf-rayet stars */
	W(WolfRayet), WN(WolfRayet), WNC(WolfRayet), WC(WolfRayet), WO(WolfRayet),

	/* carbon stars */
	CS(Carbon), C(Carbon), CN(Carbon), CJ(Carbon), CH(Carbon), CHd(Carbon),

	/* white dwarves */
	D(WhiteDwarf), DA(WhiteDwarf), DAB(WhiteDwarf), DAO(WhiteDwarf), DAZ(WhiteDwarf), DAV(WhiteDwarf), DB(WhiteDwarf),
	DBZ(WhiteDwarf), DBV(WhiteDwarf), DO(WhiteDwarf), DOV(WhiteDwarf), DQ(WhiteDwarf), DC(WhiteDwarf), DCV(WhiteDwarf),
	DX(WhiteDwarf),

	/* neutron star */
	N(Neutron),

	/* black hole */
	H(BlackHole),

	/* exotic */
	X(Exotic),

	/* unclassified */
	SupermassiveBlackHole, A_BlueWhiteSuperGiant, F_WhiteSuperGiant, M_RedSuperGiant, M_RedGiant, K_OrangeGiant,
	RoguePlanet, Nebula, StellarRemnantNebula;

	/**
	 * Represents a set of one or more star classes, grouping them into standard
	 * ranges.
	 * 
	 * @author apemanzilla
	 *
	 */
	public static enum Category {
		MainSequence, Proto, WolfRayet, Carbon, WhiteDwarf, Neutron, BlackHole, Exotic, Uncategorized
	}

	@Getter
	private final Category category;

	private StarClass(Category category) {
		this.category = category;
	}

	private StarClass() {
		this.category = Category.Uncategorized;
	}
}
