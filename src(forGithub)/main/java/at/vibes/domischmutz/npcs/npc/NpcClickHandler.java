package at.vibes.domischmutz.npcs.npc;

import at.vibes.domischmutz.npcs.npc.internal.DefaultNpcClickHandler;
import at.vibes.domischmutz.npcs.user.User;

public interface NpcClickHandler {
    static NpcClickHandler of() {
        return new DefaultNpcClickHandler();
    }

    void onClick(Npc npc, User user);
}
