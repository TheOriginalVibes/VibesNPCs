package at.vibes.domischmutz.npcs.packet;

import at.vibes.domischmutz.npcs.packet.internal.DefaultPluginPacketCache;

public interface PluginPacketCache {
    static PluginPacketCache of() {
        return new DefaultPluginPacketCache();
    }

    Object cacheResult(String key, Object result);

    void removeResult(String key);

    Object getResult(String key);

    default void removeResult(String... keys) {
        for (String key : keys) {
            removeResult(key);
        }
    }
}
