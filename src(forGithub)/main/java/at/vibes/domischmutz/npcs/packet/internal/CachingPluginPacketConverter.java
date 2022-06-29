package at.vibes.domischmutz.npcs.packet.internal;

import com.google.common.collect.ImmutableMap;
import at.vibes.domischmutz.npcs.packet.PluginPacketInfo;
import at.vibes.domischmutz.npcs.packet.PluginPacketCache;
import at.vibes.domischmutz.npcs.packet.PluginPacketConverter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CachingPluginPacketConverter<T> implements PluginPacketConverter<T> {
    private final T instance;
    private final PluginPacketCache pluginPacketCache;

    public CachingPluginPacketConverter(T instance, PluginPacketCache pluginPacketCache) {
        this.instance = instance;
        this.pluginPacketCache = pluginPacketCache;
    }

    @Override
    public T get(Class<T> clazz) {
        if (!clazz.isInterface()) {
            throw new IllegalStateException(String.format("%s must be an interface", instance.getClass().getName()));
        }
        final ImmutableMap.Builder<Method, PluginPacketInfo> builder = ImmutableMap.builder();
        for (final Method method : clazz.getDeclaredMethods()) {
            PluginPacketInfo cachedPacket = method.getAnnotation(PluginPacketInfo.class);
            if (cachedPacket == null) {
                continue;
            }
            builder.put(method, cachedPacket);
        }
        return clazz.cast(Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
            new CInvocationHandler(instance, pluginPacketCache, builder.build())));
    }

    static class CInvocationHandler implements InvocationHandler {
        private final Object instance;
        private final PluginPacketCache pluginPacketCache;

        private final ImmutableMap<Method, PluginPacketInfo> cacheMethods;

        public CInvocationHandler(
            Object instance, PluginPacketCache pluginPacketCache,
            ImmutableMap<Method, PluginPacketInfo> cacheMethods) {
            this.instance = instance;
            this.pluginPacketCache = pluginPacketCache;
            this.cacheMethods = cacheMethods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
            if (cacheMethods.containsKey(method)) {
                StringBuilder stringBuilder = new StringBuilder(cacheMethods.get(method).name());
                if (args != null) {
                    stringBuilder.append(Arrays.hashCode(args));
                }
                return pluginPacketCache.cacheResult(stringBuilder.toString(), method.invoke(instance, args));
            }
            return method.invoke(instance, args);
        }
    }
}
