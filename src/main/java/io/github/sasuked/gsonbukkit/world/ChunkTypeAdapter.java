package io.github.sasuked.gsonbukkit.world;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.lang.reflect.Type;

public class ChunkTypeAdapter implements JsonSerializer<Chunk>, JsonDeserializer<Chunk> {
	
	@Override
	public Chunk deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json == null || !json.isJsonObject()) {
			throw new JsonParseException("Invalid chunk json object.");
		}
		
		JsonObject object = (JsonObject) json;
		
		JsonElement chunkWorld = object.get("chunkWorld");
		JsonElement chunkX = object.get("chunkX");
		JsonElement chunkZ = object.get("chunkZ");
		
		if (chunkWorld == null || chunkX == null || chunkZ == null) {
			throw new JsonParseException("Malformed chunk json string!");
		}
		if (!chunkWorld.isJsonPrimitive() || !((JsonPrimitive) chunkWorld).isString()) {
			throw new JsonParseException("Invalid chunk world name string!");
		}
		if (!chunkX.isJsonPrimitive() || !((JsonPrimitive) chunkX).isNumber()) {
			throw new JsonParseException("Invalid chunk x number!");
		}
		if (!chunkZ.isJsonPrimitive() || !((JsonPrimitive) chunkZ).isNumber()) {
			throw new JsonParseException("Invalid chunk z number!");
		}
		
		World world = Bukkit.getWorld(chunkWorld.getAsString());
		if (world == null) {
			throw new NullPointerException("World " + chunkWorld.getAsString() + " not found.");
		}
		
		return world.getChunkAt(chunkX.getAsInt(), chunkZ.getAsInt());
	}
	
	@Override
	public JsonElement serialize(Chunk src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("chunkWorld", src.getWorld().getName());
		jsonObject.addProperty("chunkX", src.getX());
		jsonObject.addProperty("chunkZ", src.getZ());
		
		return jsonObject;
	}
}
