package at.vibes.domischmutz.npcs.npc.function;

import at.vibes.domischmutz.npcs.npc.internal.function.DefaultNpcFunctionRegistry;

public interface NpcFunctionRegistry {
    static NpcFunctionRegistry of() {
        return new DefaultNpcFunctionRegistry();
    }

    NpcFunction getFunction(String name);
    NpcFunction register(String name, NpcFunction function);

    default NpcFunction register(NpcFunction function) {
        return register(function.getName(), function);
    }

    NpcFunction unregister(String name);
    Iterable<NpcFunction> getFunctions();
}
