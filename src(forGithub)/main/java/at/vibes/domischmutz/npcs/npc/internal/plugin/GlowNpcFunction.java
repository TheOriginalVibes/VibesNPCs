package at.vibes.domischmutz.npcs.npc.internal.plugin;

import com.google.common.collect.ImmutableList;
import at.vibes.domischmutz.npcs.cache.CacheRegistry;
import at.vibes.domischmutz.npcs.npc.Npc;
import at.vibes.domischmutz.npcs.npc.function.NpcFunctionContext;
import at.vibes.domischmutz.npcs.npc.function.NpcFunctionValue;
import at.vibes.domischmutz.npcs.npc.internal.function.SimpleValidateSavedNpcFunction;

public class GlowNpcFunction extends SimpleValidateSavedNpcFunction {
    public GlowNpcFunction() {
        super("glow", ImmutableList.of(
            new NpcFunctionValue.DefaultValue<>("data", String.class),
            new NpcFunctionValue.DefaultValue<>("enabled",  Boolean.class)));
    }

    @Override
    protected void function(Npc npc, NpcFunctionContext functionContext) throws Exception {
        final String glowColorName = (String) functionContext.getAttribute("glowName");
        final Object glowColor = CacheRegistry.ENUM_CHAT_FORMAT_FIND.invoke(null,
            glowColorName == null || glowColorName.length() == 0 ? "WHITE" : glowColorName);
        CacheRegistry.SET_DATA_WATCHER_METHOD.invoke(CacheRegistry.GET_DATA_WATCHER_METHOD.invoke(npc),
            CacheRegistry.DATA_WATCHER_OBJECT_CONSTRUCTOR.newInstance(
                0, CacheRegistry.DATA_WATCHER_REGISTER_FIELD),
            (Boolean) functionContext.getAttribute("enabled") ? (byte) 0x40 : (byte) 0x0);
        //npc.getPackets().getCache().removeResult("scoreboardPackets");
        super.function(npc, functionContext);
    }
}
