package at.vibes.domischmutz.npcs.npc.internal;

import at.vibes.domischmutz.npcs.npc.Npc;
import at.vibes.domischmutz.npcs.npc.NpcClickHandler;
import at.vibes.domischmutz.npcs.user.User;

public class DefaultNpcClickHandler implements NpcClickHandler {
    @Override
    public void onClick(Npc npc, User user) {
        user.getPlayer().sendMessage("TEST");
    }
}
