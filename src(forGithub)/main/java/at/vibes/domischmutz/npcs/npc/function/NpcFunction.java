package at.vibes.domischmutz.npcs.npc.function;

import at.vibes.domischmutz.npcs.npc.internal.function.DefaultNpcContextBuilder;
import at.vibes.domischmutz.npcs.npc.Npc;
import at.vibes.domischmutz.npcs.npc.NpcModel;

public interface NpcFunction {
    String getName();

    void executeFunction(Npc npc, NpcFunctionContext functionContext);

    default void executeFunction(Npc npc) {
        executeFunction(npc, resolve(npc));
    }

    default NpcFunctionContext resolve(Npc npc) {
        return new DefaultNpcContextBuilder().build();
    }
}
