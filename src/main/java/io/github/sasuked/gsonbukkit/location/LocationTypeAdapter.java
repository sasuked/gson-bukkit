package io.github.sasuked.gsonbukkit.location;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;

public class LocationTypeAdapter implements JsonSerializer<Location>, JsonDeserializer<Location> {
	
	@Override
	public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json == null || !json.isJsonObject()) {
			throw new JsonParseException("Inválid JsonObject.");
		}
		
		JsonObject obj = (JsonObject) json;
		JsonElement world = obj.get("world");
		JsonElement x = obj.get("x");
		JsonElement y = obj.get("y");
		JsonElement z = obj.get("z");
		JsonElement yaw = obj.get("yaw");
		JsonElement pitch = obj.get("pitch");
		
		if (world == null || x == null || y == null || z == null || yaw == null || pitch == null) {
			throw new JsonParseException("Malformed location json string!");
		}
		
		if (!world.isJsonPrimitive() || !((JsonPrimitive) world).isString()) {
			throw new JsonParseException("world is not a string");
		}
		
		if (!x.isJsonPrimitive() || !((JsonPrimitive) x).isNumber()) {
			throw new JsonParseException("x is not a number");
		}
		
		if (!y.isJsonPrimitive() || !((JsonPrimitive) y).isNumber()) {
			throw new JsonParseException("y is not a number");
		}
		
		if (!z.isJsonPrimitive() || !((JsonPrimitive) z).isNumber()) {
			throw new JsonParseException("z is not a number");
		}
		
		if (!yaw.isJsonPrimitive() || !((JsonPrimitive) yaw).isNumber()) {
			throw new JsonParseException("yaw is not a number");
		}
		
		if (!pitch.isJsonPrimitive() || !((JsonPrimitive) pitch).isNumber()) {
			throw new JsonParseException("pitch is not a number");
		}
		
		World bukkitWorld = Bukkit.getWorld(world.getAsString());
		if (bukkitWorld == null) {
			throw new IllegalArgumentException("Inválid/Null loaded world");
		}
		
		return new Location(bukkitWorld, x.getAsDouble(), y.getAsDouble(), z.getAsDouble(), yaw.getAsFloat(), pitch.getAsFloat());
	}
	
	@Override
	public JsonElement serialize(Location source, Type typeOfSrc, JsonSerializationContext context) {
		if (source == null) {
			throw new NullPointerException("Invalid location source.");
		}
		World sourceWorld = source.getWorld();
		if (sourceWorld == null) {
			throw new NullPointerException("Invalid location world.");
		}
		
		JsonObject object = new JsonObject();
		object.addProperty("world", sourceWorld.getName());
		object.addProperty("x", source.getX());
		object.addProperty("y", source.getY());
		object.addProperty("z", source.getZ());
		object.addProperty("yaw", source.getYaw());
		object.addProperty("pitch", source.getPitch());
		return object;
	}
}
