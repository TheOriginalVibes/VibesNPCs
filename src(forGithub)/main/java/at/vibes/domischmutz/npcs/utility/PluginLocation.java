package at.vibes.domischmutz.npcs.utility;

import com.google.common.base.Objects;
import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.lang.reflect.Type;

public class PluginLocation {
    public static final ZLocationSerializer SERIALIZER = new ZLocationSerializer();

    private final String worldName;
    private final double x, y, z;
    private final float yaw, pitch;
    private Location bukkitLocation;

    public PluginLocation(String worldName, double x, double y, double z, float yaw, float pitch) {
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public PluginLocation(Location location) {
        this(
            location.getWorld().getName(),
            location.getX(),
            location.getY(),
            location.getZ(),
            location.getYaw(),
            location.getPitch());
    }

    public String getWorldName() {
        return worldName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public double distance(PluginLocation pluginLocation) {
        return Math.sqrt((this.x - pluginLocation.x) * 2) + ((this.z - pluginLocation.z) * 2);
    }

    public Location bukkitLocation() {
        if (bukkitLocation != null) {
            return bukkitLocation;
        }
        return bukkitLocation = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    public Vector toVector() {
        return bukkitLocation().toVector();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PluginLocation that = (PluginLocation) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.z, z) == 0 && Objects.equal(worldName, that.worldName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(worldName, x, y, z);
    }

    static class ZLocationSerializer
            implements JsonSerializer<PluginLocation>, JsonDeserializer<PluginLocation> {
        @Override
        public JsonElement serialize(
            PluginLocation src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("world", src.getWorldName());
            jsonObject.addProperty("x", src.getX());
            jsonObject.addProperty("y", src.getY());
            jsonObject.addProperty("z", src.getZ());
            jsonObject.addProperty("yaw", src.getYaw());
            jsonObject.addProperty("pitch", src.getPitch());
            return jsonObject;
        }

        @Override
        public PluginLocation deserialize(
                JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            return new PluginLocation(
                    jsonObject.get("world").getAsString(),
                    jsonObject.get("x").getAsDouble(),
                    jsonObject.get("y").getAsDouble(),
                    jsonObject.get("z").getAsDouble(),
                    jsonObject.get("yaw").getAsFloat(),
                    jsonObject.get("pitch").getAsFloat());
        }
    }
}
