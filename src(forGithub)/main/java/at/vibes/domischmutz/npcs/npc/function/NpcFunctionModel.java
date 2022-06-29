package at.vibes.domischmutz.npcs.npc.function;

import java.util.Map;

public class NpcFunctionModel {
    private final boolean enabled;
    private final Map<String, Object> attributes;

    public NpcFunctionModel(NpcFunctionContext functionContext, boolean enabled) {
        this.enabled = enabled;
        this.attributes = functionContext.attributes();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
