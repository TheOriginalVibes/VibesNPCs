package at.vibes.domischmutz.npcs.npc;

import at.vibes.domischmutz.npcs.npc.internal.PluginNpcStore;

public interface NpcStore {

    static NpcStore of() {
        return new PluginNpcStore();
    }
    void init() throws Exception;

    Npc getNpc(int id);
    Npc addNpc(int id, Npc npc);

    void removeNpc(int id);
    Iterable<Npc> getNpcs();
}
