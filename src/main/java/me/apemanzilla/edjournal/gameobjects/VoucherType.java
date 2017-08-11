package me.apemanzilla.edjournal.gameobjects;

import com.google.gson.annotations.SerializedName;

public enum VoucherType {
	CombatBond,
	@SerializedName("bounty")
	Bounty,
	Trade,
	Settlement,
	Scannable
}
