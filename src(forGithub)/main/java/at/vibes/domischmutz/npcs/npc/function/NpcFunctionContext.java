package at.vibes.domischmutz.npcs.npc.function;

import at.vibes.domischmutz.npcs.npc.internal.function.DefaultNpcContextBuilder;
import at.vibes.domischmutz.npcs.npc.Npc;

import java.util.Map;

public interface NpcFunctionContext {
    NpcFunctionContext NULL_CONTEXT = () -> null;

    static FunctionContextBuilder builder() {
        return new DefaultNpcContextBuilder();
    }

    Map<String, Object> attributes();

    default Object getAttribute(String name) {
        return attributes().get(name);
    }

    interface FunctionContextBuilder {
        FunctionContextBuilder merge(Map<String, Object> map);
        NpcFunctionContext build();
    }
}
