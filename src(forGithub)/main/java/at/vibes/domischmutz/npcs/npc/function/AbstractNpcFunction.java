package at.vibes.domischmutz.npcs.npc.function;

import com.google.common.collect.ImmutableList;
import at.vibes.domischmutz.npcs.npc.Npc;

public abstract class AbstractNpcFunction implements NpcFunction {
    private final String name;
    protected final ImmutableList<NpcFunctionValue<?>> requiredValues;

    public AbstractNpcFunction(String name) {
        this(name, ImmutableList.of());
    }

    public AbstractNpcFunction(String name, ImmutableList<NpcFunctionValue<?>> requiredValues) {
        this.name = name;
        this.requiredValues = requiredValues;
    }

    public String getName() {
        return name;
    }

    protected void validateContext(NpcFunctionContext functionContext)
        throws ValidateNpcFunctionException {}

    protected abstract void function(Npc npc, NpcFunctionContext functionContext) throws Exception;

    protected abstract boolean isSaveAllowed();

    public void executeFunction(Npc npc, NpcFunctionContext functionContext)
            throws ExecuteNpcFunctionException, ValidateNpcFunctionException {
        validateContext(functionContext);
        if (isSaveAllowed()) {
            npc.getModel().getFunctions().put(name, new NpcFunctionModel(functionContext, !isTrue(npc)));
        }
        try {
            function(npc, functionContext);
        } catch (Exception exception) {
            throw new ExecuteNpcFunctionException("cannot execute function for npc");
        }
    }

    public boolean isTrue(Npc npc) {
        return NpcFunctions.isTrue(npc, this);
    }
}
