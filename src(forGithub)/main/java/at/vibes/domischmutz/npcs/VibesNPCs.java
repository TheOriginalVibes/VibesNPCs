package at.vibes.domischmutz.npcs;

import at.vibes.domischmutz.npcs.command.PluginCommand;
import at.vibes.domischmutz.npcs.command.PluginCommandExec;
import at.vibes.domischmutz.npcs.command.internal.DefaultPluginSubCommandFinder;
import at.vibes.domischmutz.npcs.command.internal.plugin.*;
import at.vibes.domischmutz.npcs.configuration.Configuration;
import at.vibes.domischmutz.npcs.configuration.ConfigurationConstants;
import at.vibes.domischmutz.npcs.configuration.ConfigurationSaveTask;
import at.vibes.domischmutz.npcs.listener.PlayerListener;
import at.vibes.domischmutz.npcs.npc.internal.plugin.GlowNpcFunction;
import at.vibes.domischmutz.npcs.setting.PluginSettings;
import at.vibes.domischmutz.npcs.user.User;
import at.vibes.domischmutz.npcs.utility.BungeeUtils;
import at.vibes.domischmutz.npcs.utility.MetricsLite;
import at.vibes.domischmutz.npcs.utility.PluginLocation;
import at.vibes.domischmutz.npcs.utility.SchedulerUtils;
import at.vibes.domischmutz.npcs.utility.itemstack.ItemStackSerializer;
import com.google.common.collect.ImmutableList;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

/**
 * @author Viiiiibes
 */
public class VibesNPCs extends JavaPlugin {
    private static final String PLUGIN_NAME = "VNPCs";

    public static final File PLUGIN_FOLDER = new File("plugins/" + PLUGIN_NAME);
    public static final File PATH_FOLDER = new File("plugins/" + PLUGIN_NAME + "/paths");

    private static final ImmutableList<File> FILES = ImmutableList.of(PLUGIN_FOLDER, PATH_FOLDER);

    /*
    *
    * Vorbauserver i hope we're done now
    * I will never code anything for you again..
    *
    * Fuck you!
    * (I swear to god do not leak my code again..)
    * 
     */

    public static final PluginSettings SETTINGS = PluginSettings.builder()
        .withGson(new GsonBuilder()
            .registerTypeAdapter(PluginLocation.class, PluginLocation.SERIALIZER)
            .registerTypeHierarchyAdapter(ItemStack.class, new ItemStackSerializer())
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create())
        .build();

    private static final int PLUGIN_ID = 8054;

    public static SchedulerUtils SCHEDULER;
    public static BungeeUtils BUNGEE_UTILS;

    @Override
    public void onEnable() { ;
        for (File file : FILES) {
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    disablePlugin(String.format("Could not create folder %s", file.getName()));
                    return;
                }
            }
        }

        SCHEDULER = new SchedulerUtils(this);
        try {
            SETTINGS.init();
            SETTINGS.getNpcFunctionRegistry().register(new GlowNpcFunction());
        } catch (Exception exception) {
            exception.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        BUNGEE_UTILS = new BungeeUtils(this);

        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerListener(), this);

        final PluginCommand baseCommand = PluginCommand.builder("vnpcs")
            .addSubCommand(new NpcCreateSubCommand())
            .addSubCommand(new NpcDeleteSubCommand())
            .addSubCommand(new NpcSetSkinSubCommand())
            .addSubCommand(new NpcToggleSubCommand())
            .addSubCommand(new NpcListSubCommand())
            .addSubCommand(new NpcMoveSubCommand())
            .addSubCommand(new NpcTeleportCommand())
            .addSubCommand(new NpcEquipSubCommand())
            .addSubCommand(new NpcAddLineCommand())
            .addSubCommand(new NpcRemoveLineCommand())
            .build();
        baseCommand.init(PluginCommandExec.of(baseCommand, DefaultPluginSubCommandFinder.INSTANCE));

        new MetricsLite(this, PLUGIN_ID);

        new PlayerListener();

        new ConfigurationSaveTask(ConfigurationConstants.SAVE_DELAY);

        for (final Player player : getServer().getOnlinePlayers()) {
            try {
                SETTINGS.getUserStore().addUser(User.of(player));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        Configuration.SAVE_CONFIGURATIONS.forEach(Configuration::save);
        SETTINGS.getNpcStore().getNpcs().forEach(npc -> {
            try {
                npc.onDisable();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        SETTINGS.getAsyncHttpClient().shutdown();
    }

    private void disablePlugin(String disableMessage) {
        getServer().getPluginManager().disablePlugin(this);
        getLogger().log(Level.INFO, disableMessage);

    }
}
