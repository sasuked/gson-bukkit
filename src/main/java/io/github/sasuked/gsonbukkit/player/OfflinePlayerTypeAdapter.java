package io.github.sasuked.gsonbukkit.player;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.Type;

public class OfflinePlayerTypeAdapter implements JsonSerializer<OfflinePlayer> , JsonDeserializer<OfflinePlayer> {
	
	@Override
	public OfflinePlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Bukkit.getOfflinePlayer(json.getAsString());
	}
	
	@Override
	public JsonElement serialize(OfflinePlayer src, Type typeOfSrc, JsonSerializationContext context) {
		if(src == null){
			throw new NullPointerException("Invalid offline player instance.");
		}
		
		return new JsonPrimitive(src.getUniqueId().toString());
	}
}
