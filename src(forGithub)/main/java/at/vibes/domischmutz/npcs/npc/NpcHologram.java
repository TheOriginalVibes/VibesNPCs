package at.vibes.domischmutz.npcs.npc;

import at.vibes.domischmutz.npcs.entity.PluginEntity;

public interface NpcHologram {
    void create() throws Exception;

    Iterable<? extends PluginEntity> getPluginEntity();
}
