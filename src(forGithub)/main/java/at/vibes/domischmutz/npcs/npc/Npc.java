package at.vibes.domischmutz.npcs.npc;

import at.vibes.domischmutz.npcs.entity.PluginEntity;
import at.vibes.domischmutz.npcs.entity.PluginEntityFactory;
import at.vibes.domischmutz.npcs.npc.internal.DefaultNpc;
import org.bukkit.Location;

import java.util.UUID;

public interface Npc {
    static Npc of(PluginEntityFactory<?> factory, NpcModel model,
                  NpcName npcName, NpcClickHandler clickHandler) {
        return new DefaultNpc(factory, model, npcName, clickHandler);
    }

    static Npc of(PluginEntityFactory<?> factory, NpcModel model) throws Exception {
        Npc npc = of(factory, model, NpcName.of(), NpcClickHandler.of());
        try {
            npc.init();
            return npc;
        } catch (Exception exception) {
            throw new IllegalStateException("err" , exception);
        }
    }

    void init() throws Exception;
    void onDisable() throws Exception;
    UUID getUUID();
    String getName();
    Location getLocation();
    boolean isLoaded();
    PluginEntity getPluginEntity();
    NpcModel getModel();
    NpcHologram getHologram();
    NpcClickHandler getClickHandler();
}
