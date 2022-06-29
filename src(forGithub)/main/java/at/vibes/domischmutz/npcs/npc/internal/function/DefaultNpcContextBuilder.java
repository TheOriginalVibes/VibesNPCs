package at.vibes.domischmutz.npcs.npc.internal.function;

import java.util.HashMap;
import java.util.Map;

import at.vibes.domischmutz.npcs.npc.function.NpcFunctionContext;

public class DefaultNpcContextBuilder implements NpcFunctionContext.FunctionContextBuilder {

    protected static Map<String, Object> attributes;

    public DefaultNpcContextBuilder() {
        this(new HashMap<>());
    }

    protected DefaultNpcContextBuilder(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public static NpcFunctionContext.FunctionContextBuilder addAttribute(String string, Object object) {
        attributes.put(string, object);
        return null;
    }

    @Override
    public NpcFunctionContext.FunctionContextBuilder merge(Map<String, Object> map) {
        attributes.putAll(map);
        return this;
    }

    @Override
    public NpcFunctionContext build() {
        return () -> attributes;
    }
}
