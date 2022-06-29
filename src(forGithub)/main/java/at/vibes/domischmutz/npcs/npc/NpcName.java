package at.vibes.domischmutz.npcs.npc;

import at.vibes.domischmutz.npcs.npc.internal.DefaultNpcName;

public interface NpcName {
    static NpcName of() {
        return DefaultNpcName.INSTANCE;
    }

    String generateNpcName(Npc npc);
}
