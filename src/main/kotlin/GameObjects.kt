package me.apemanzilla.edjournal

import com.google.gson.annotations.SerializedName

/** An item stored in ship cargo */
data class CargoItem(
	/** The quantity of the item */
	@SerializedName("Count") val count: Int,

	/** How many of the items are stolen */
	@SerializedName("Stolen") val stolen: Int,

	/** The raw name of the item */
	@SerializedName("Name") val name: String,

	/** The localized name of the item, or `null` if it would be equivalent to [name] */
	@SerializedName("Name_Localised") val nameLocalized: String?
)
