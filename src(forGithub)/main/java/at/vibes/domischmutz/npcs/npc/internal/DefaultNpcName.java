package at.vibes.domischmutz.npcs.npc.internal;

import at.vibes.domischmutz.npcs.npc.Npc;
import at.vibes.domischmutz.npcs.npc.NpcName;
import at.vibes.domischmutz.npcs.utility.Utils;

public class DefaultNpcName implements NpcName {
    public static final NpcName INSTANCE = new DefaultNpcName();

    @Override
    public String generateNpcName(Npc npc) {
        return Utils.randomString(6);
    }
}
