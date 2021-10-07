package io.github.sasuked.gsonbukkit.enchantment;

import com.google.gson.*;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Type;

public class EnchantmentTypeAdapter implements JsonSerializer<Enchantment>, JsonDeserializer<Enchantment> {
	
	@Override
	public Enchantment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Enchantment.getByName(json.getAsString());
	}
	
	@Override
	public JsonElement serialize(Enchantment src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.getName());
	}
}
