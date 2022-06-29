package at.vibes.domischmutz.npcs.npc.internal;

import at.vibes.domischmutz.npcs.configuration.Configuration;
import at.vibes.domischmutz.npcs.configuration.ConfigurationValue;
import at.vibes.domischmutz.npcs.entity.*;
import at.vibes.domischmutz.npcs.npc.NpcHologram;
import at.vibes.domischmutz.npcs.npc.NpcModel;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class PluginNpcHologram implements NpcHologram {
    private static final PluginEntityType PLUGIN_ENTITY_TYPE = PluginEntityTypes.ARMOR_STAND;
    private static final double LINE_SPACING = Configuration.CONFIGURATION.getValue(ConfigurationValue.LINE_SPACING);

    private final PluginEntityFactory<?> factory;
    private final NpcModel npcModel;

    private final List<PluginEntity> pluginEntities = new ArrayList<>();

    public PluginNpcHologram(
        PluginEntityFactory<?> factory, NpcModel npcModel) {
        this.factory = factory;
        this.npcModel = npcModel;
    }

    @Override
    public void create() throws Exception {
        final Location location = npcModel.getLocation().bukkitLocation();
        for (final String line : npcModel.getHologramLines()) {
            PluginEntity entity = factory.createPluginEntity(PLUGIN_ENTITY_TYPE,  PluginEntitySpec.of(location));
            entity.setLocation(location);
            entity.setName(line);
            entity.setInvisible(true);
            pluginEntities.add(entity);
        }
    }

    @Override
    public Iterable<PluginEntity> getPluginEntity() {
        return pluginEntities;
    }
}
