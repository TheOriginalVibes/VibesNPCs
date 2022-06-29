package at.vibes.domischmutz.npcs.packet;

import at.vibes.domischmutz.npcs.packet.internal.CachingPluginPacketConverter;

@FunctionalInterface
public interface PluginPacketConverter<T> {
    static <T> T of(T instance, Class<T> interfaceClass, PluginPacketCache cache) {
        return new CachingPluginPacketConverter<>(instance, cache).get(interfaceClass);
    }

    T get(Class<T> clazz);
}
