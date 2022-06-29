package at.vibes.domischmutz.npcs.npc.internal.function;

import com.google.common.collect.ImmutableList;
import at.vibes.domischmutz.npcs.npc.Npc;
import at.vibes.domischmutz.npcs.npc.function.AbstractNpcFunction;
import at.vibes.domischmutz.npcs.npc.function.NpcFunctionContext;
import at.vibes.domischmutz.npcs.npc.function.NpcFunctionValue;
import at.vibes.domischmutz.npcs.npc.function.ValidateNpcFunctionException;

public class SimpleValidateSavedNpcFunction extends SelfUpdateSavedNpcFunction {
    public SimpleValidateSavedNpcFunction(String name, ImmutableList<NpcFunctionValue<?>> requiredValues) {
        super(name, requiredValues);
    }

    @Override
    protected void validateContext(NpcFunctionContext functionContext)
            throws ValidateNpcFunctionException {
        super.validateContext(functionContext);
        for (NpcFunctionValue<?> functionValue : requiredValues) {
            Object mapValue = functionContext.attributes().get(functionValue.name());
            if (mapValue == null) {
                throw new ValidateNpcFunctionException("can't find value for " + functionValue.name());
            }
            if (!mapValue.getClass().isAssignableFrom(functionValue.getType())) {
                throw new ValidateNpcFunctionException(
                    mapValue.getClass().getSimpleName() + " should be a subclass of " +
                        functionValue.getType().getSimpleName());
            }
        }
    }

    @Override
    public NpcFunctionContext resolve(Npc npc) {
        return new DefaultNpcContextBuilder()
            .merge(npc.getModel().getFunctions().get(getName()).getAttributes())
            .build();
    }
}
