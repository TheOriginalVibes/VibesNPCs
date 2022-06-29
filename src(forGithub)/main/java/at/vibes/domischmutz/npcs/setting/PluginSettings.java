package at.vibes.domischmutz.npcs.setting;

import at.vibes.domischmutz.npcs.npc.function.NpcFunctionRegistry;
import at.vibes.domischmutz.npcs.setting.internal.DefaultPluginSettingsBuilder;
import com.google.gson.Gson;
import at.vibes.domischmutz.npcs.npc.NpcStore;
import at.vibes.domischmutz.npcs.http.AsyncHttpClient;
import at.vibes.domischmutz.npcs.task.TaskManager;
import at.vibes.domischmutz.npcs.user.UserStore;

public interface PluginSettings {
    static PluginSettingsBuilder builder() {
        return new DefaultPluginSettingsBuilder();
    }

    default void init() throws Exception {};

    AsyncHttpClient getAsyncHttpClient();
    NpcStore getNpcStore();
    UserStore getUserStore();
    NpcFunctionRegistry getNpcFunctionRegistry();
    TaskManager getTaskManager();
    Gson getGson();

    interface PluginSettingsBuilder {

        PluginSettingsBuilder withHttpClient(AsyncHttpClient httpClient);
        PluginSettingsBuilder withNpcStore(NpcStore npcStore);
        PluginSettingsBuilder withUserStore(UserStore userStore);
        PluginSettingsBuilder withNpcFunctionRegistry(NpcFunctionRegistry functionRegistry);
        PluginSettingsBuilder withGson(Gson gson);
        PluginSettingsBuilder withTaskManager(TaskManager taskManager);

        PluginSettings build();
    }
}
