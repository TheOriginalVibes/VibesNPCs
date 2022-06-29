package at.vibes.domischmutz.npcs.packet;

public interface PluginPacket {

    interface Cached extends PluginPacket {
        PluginPacketCache getCache();
    }
}
