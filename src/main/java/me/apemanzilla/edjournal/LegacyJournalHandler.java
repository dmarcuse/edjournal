package me.apemanzilla.edjournal;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.primitives.Doubles;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import me.apemanzilla.edjournal.gameobjects.Material;
import me.apemanzilla.edjournal.gameobjects.MaterialPercentage;

class LegacyJournalHandler implements TypeAdapterFactory {
	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		if (type.equals(new TypeToken<List<MaterialPercentage>>() {})) {
			// fix old format of events using material percentages (e.g. detailed planet scans)
			return new TypeAdapter<T>() {
				@Override
				public void write(JsonWriter out, T value) throws IOException {
					gson.getDelegateAdapter(LegacyJournalHandler.this, type).write(out, value);
				}

				@SuppressWarnings("unchecked")
				@Override
				public T read(JsonReader in) throws IOException {
					switch (in.peek()) {
					case BEGIN_OBJECT:
						return (T) gson
								.getDelegateAdapter(LegacyJournalHandler.this, new TypeToken<Map<String, Double>>() {})
								.read(in).entrySet().stream()
								.map(e -> new MaterialPercentage(e.getKey(), e.getValue()))
								.sorted((a, b) -> Doubles.compare(b.getPercent(), a.getPercent()))
								.collect(Collectors.toList());
					default:
						return gson.getDelegateAdapter(LegacyJournalHandler.this, type).read(in);
					}
				}
			};
		} else if (type.equals(new TypeToken<List<Material>>() {})) {
			// fix old format of events using material counts (e.g. engineer crafting)
			return new TypeAdapter<T>() {
				@Override
				public void write(JsonWriter out, T value) throws IOException {
					gson.getDelegateAdapter(LegacyJournalHandler.this, type).write(out, value);
				}

				@SuppressWarnings("unchecked")
				@Override
				public T read(JsonReader in) throws IOException {
					switch (in.peek()) {
					case BEGIN_OBJECT:
						return (T) gson
								.getDelegateAdapter(LegacyJournalHandler.this, new TypeToken<Map<String, Integer>>() {})
								.read(in).entrySet().stream().map(e -> new Material(e.getKey(), e.getValue()))
								.collect(Collectors.toList());
					default:
						return gson.getDelegateAdapter(LegacyJournalHandler.this, type).read(in);
					}
				}
			};
		}

		return null;
	}
}
