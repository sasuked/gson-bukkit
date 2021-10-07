package io.github.sasuked.gsonbukkit.sound;

import com.google.gson.*;
import org.bukkit.Sound;

import java.lang.reflect.Type;

public class SoundTypeAdapter implements JsonSerializer<Sound> , JsonDeserializer<Sound> {
	@Override
	public Sound deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Sound.valueOf(json.getAsString());
	}
	
	@Override
	public JsonElement serialize(Sound src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.name());
	}
}
