package at.vibes.domischmutz.npcs.npc.function;

import static at.vibes.domischmutz.npcs.utility.GuavaCollectors.toImmutableList;
import static at.vibes.domischmutz.npcs.utility.GuavaCollectors.toImmutableMap;

import at.vibes.domischmutz.npcs.npc.internal.function.SelfUpdateSavedNpcFunction;
import at.vibes.domischmutz.npcs.npc.internal.plugin.GlowNpcFunction;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import at.vibes.domischmutz.npcs.npc.Npc;

public final class NpcFunctions {

    public static final ImmutableList<NpcFunction> WITHOUT_FUNCTION =
        ImmutableList.of(
            new SelfUpdateSavedNpcFunction("look"),
            new SelfUpdateSavedNpcFunction("holo"),
            new SelfUpdateSavedNpcFunction("mirror"));

    public static final ImmutableList<NpcFunction> WITH_FUNCTION =
        ImmutableList.of(new GlowNpcFunction());

    public static final ImmutableList<NpcFunction> ALLOWED_FUNCTIONS =
        ImmutableList.<NpcFunction>builder()
            .addAll(WITHOUT_FUNCTION)
            .addAll(WITH_FUNCTION)
            .build();

    public static final ImmutableMap<String, NpcFunction> BY_NAME =
        ALLOWED_FUNCTIONS.stream().collect(toImmutableMap(
            NpcFunction::getName, function -> function));

    public static NpcFunction findFunctionForName(String name) {
        return BY_NAME.get(name);
    }

    public static ImmutableList<NpcFunction> findFunctionsForNpc(Npc npc) {
        return ALLOWED_FUNCTIONS.stream().filter(function -> isTrue(npc, function)).collect(toImmutableList());
    }

    public static boolean isTrue(Npc npc, NpcFunction function) {
        final NpcFunctionModel functionModel = npc.getModel().getFunctions().get(function.getName());
        return functionModel != null && functionModel.isEnabled();
    }

    public static boolean isTrue(Npc npc, String function) {
        return isTrue(npc, findFunctionForName(function));
    }

    private NpcFunctions() {}
}
