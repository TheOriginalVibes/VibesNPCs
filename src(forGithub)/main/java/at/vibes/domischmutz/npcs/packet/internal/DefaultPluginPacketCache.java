package at.vibes.domischmutz.npcs.packet.internal;

import at.vibes.domischmutz.npcs.packet.PluginPacketCache;

import java.util.HashMap;
import java.util.Map;

public class DefaultPluginPacketCache implements PluginPacketCache {
    private final Map<String, Object> cache = new HashMap<>();

    @Override
    public Object cacheResult(String key, Object result) {
        return cache.computeIfAbsent(key, (u) -> result);
    }

    @Override
    public void removeResult(String key) {
        cache.entrySet().removeIf(entry -> entry.getKey().startsWith(key));
    }

    @Override
    public Object getResult(String key) {
        return cache.get(key);
    }
}
