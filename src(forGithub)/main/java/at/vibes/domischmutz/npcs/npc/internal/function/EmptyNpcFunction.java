package at.vibes.domischmutz.npcs.npc.internal.function;

import com.google.common.collect.ImmutableList;
import at.vibes.domischmutz.npcs.npc.Npc;
import at.vibes.domischmutz.npcs.npc.function.AbstractNpcFunction;
import at.vibes.domischmutz.npcs.npc.function.NpcFunctionContext;
import at.vibes.domischmutz.npcs.npc.function.NpcFunctionValue;

public class EmptyNpcFunction extends AbstractNpcFunction {
    public EmptyNpcFunction(String name) {
        super(name);
    }

    public EmptyNpcFunction(String name, ImmutableList<NpcFunctionValue<?>> requiredValues) {
        super(name, requiredValues);
    }

    @Override
    protected void function(Npc npc, NpcFunctionContext functionContext) throws Exception {}

    @Override
    protected boolean isSaveAllowed() {
        return false;
    }
}
