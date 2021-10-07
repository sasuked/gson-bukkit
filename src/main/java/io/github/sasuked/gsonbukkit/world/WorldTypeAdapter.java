package io.github.sasuked.gsonbukkit.world;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.lang.reflect.Type;

public class WorldTypeAdapter implements JsonSerializer<World>, JsonDeserializer<World> {
	
	@Override
	public World deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return Bukkit.getWorld(jsonElement.getAsString());
	}
	
	@Override
	public JsonElement serialize(World world, Type type, JsonSerializationContext jsonSerializationContext) {
		if (world == null) {
			return JsonNull.INSTANCE;
		}
		
		return new JsonPrimitive(world.getName());
	}
}