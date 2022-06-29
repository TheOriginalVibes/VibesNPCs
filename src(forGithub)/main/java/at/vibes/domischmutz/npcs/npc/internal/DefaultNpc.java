package at.vibes.domischmutz.npcs.npc.internal;

import at.vibes.domischmutz.npcs.VibesNPCs;
import at.vibes.domischmutz.npcs.entity.PluginEntityFactory;
import at.vibes.domischmutz.npcs.npc.AbstractNpc;
import at.vibes.domischmutz.npcs.npc.NpcClickHandler;
import at.vibes.domischmutz.npcs.npc.NpcModel;
import at.vibes.domischmutz.npcs.npc.NpcName;

public class DefaultNpc extends AbstractNpc {
    public DefaultNpc(PluginEntityFactory<?> pluginEntityFactory, NpcModel npcModel,
                      NpcName npcName, NpcClickHandler clickHandler) {
        super(pluginEntityFactory, npcModel, npcName, clickHandler, VibesNPCs.SETTINGS.getTaskManager());
    }

    @Override
    protected void load() {}
}
