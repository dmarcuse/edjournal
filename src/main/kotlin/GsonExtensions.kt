package me.apemanzilla.edjournal

import com.google.gson.*
import com.google.gson.reflect.TypeToken

/** Converts [json] from JSON to [T] using this [Gson] instance. Works with generic types/ */
inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)

/** Converts [json] from JSON to [T] using this [Gson] instance. Works with generic types/ */
inline fun <reified T> Gson.fromJson(json: JsonElement) = fromJson<T>(json, object : TypeToken<T>() {}.type)

/** Registers a [TypeAdapter] for type [T] */
inline fun <reified T> GsonBuilder.registerTypeAdapter(a: TypeAdapter<T>) =
	registerTypeAdapter(object : TypeToken<T>() {}.type, a)

/** Registers a [JsonDeserializer] and [JsonSerializer] for type [T] */
inline fun <reified T, A> GsonBuilder.registerTypeAdapter(d: A) where A : JsonDeserializer<T>, A : JsonSerializer<T> =
	registerTypeAdapter(object : TypeToken<T>() {}.type, d)
