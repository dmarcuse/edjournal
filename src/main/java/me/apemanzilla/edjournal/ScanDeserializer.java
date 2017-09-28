package me.apemanzilla.edjournal;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.apemanzilla.edjournal.events.Scan;

class ScanDeserializer implements JsonDeserializer<Scan> {
	@Override
	public Scan deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject obj = json.getAsJsonObject();
		if (obj.has("StarType")) {
			return context.deserialize(json, Scan.StarScan.class);
		} else {
			if (!obj.has("SurfaceTemperature")) {
				return context.deserialize(json, Scan.BasicBodyScan.class);
			} else {
				return context.deserialize(json, Scan.DetailedBodyScan.class);
			}
		}
	}
}
