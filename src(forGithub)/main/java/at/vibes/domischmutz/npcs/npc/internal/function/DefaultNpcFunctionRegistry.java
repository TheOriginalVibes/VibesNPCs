package at.vibes.domischmutz.npcs.npc.internal.function;

import at.vibes.domischmutz.npcs.npc.function.NpcFunctionRegistry;
import at.vibes.domischmutz.npcs.npc.function.NpcFunction;

import java.util.HashMap;
import java.util.Map;

public class DefaultNpcFunctionRegistry implements NpcFunctionRegistry {
    private final Map<String, NpcFunction> functions = new HashMap<>();

    @Override
    public NpcFunction getFunction(String name) {
        return functions.get(name);
    }

    @Override
    public NpcFunction register(String name, NpcFunction function) {
        return functions.computeIfAbsent(name, (n) -> function);
    }

    @Override
    public NpcFunction unregister(String name) {
        return functions.remove(name);
    }

    @Override
    public Iterable<NpcFunction> getFunctions() {
        return functions.values();
    }
}
