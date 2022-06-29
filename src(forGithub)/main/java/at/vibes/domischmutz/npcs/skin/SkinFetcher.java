package at.vibes.domischmutz.npcs.skin;

import at.vibes.domischmutz.npcs.skin.internal.DefaultSkinFetcherBuilder;
import at.vibes.domischmutz.npcs.VibesNPCs;
import at.vibes.domischmutz.npcs.http.AsyncHttpClient;
import at.vibes.domischmutz.npcs.http.HttpMethod;

import java.util.function.Consumer;

public interface SkinFetcher {
    String DEFAULT_SKIN_NAME = "Viiiiibes";

    static SkinFetcherBuilder builder() {
        return new DefaultSkinFetcherBuilder();
    }

    static SkinFetcher of(String skin) {
        return builder()
            .withSkin(skin)
            .withClient(VibesNPCs.SETTINGS.getAsyncHttpClient())
            .withServer(SkinFetcherService.of(skin.startsWith("http") ? HttpMethod.POST : HttpMethod.GET))
            .build();
    }

    void fetch(Consumer<SkinFetcherResult> onSuccess, Consumer<Throwable> onError) throws Exception;

    interface SkinFetcherBuilder {

        SkinFetcherBuilder withSkin(String name);
        SkinFetcherBuilder withClient(AsyncHttpClient httpClient);
        SkinFetcherBuilder withServer(SkinFetcherService server);
        SkinFetcherBuilder withTimeout(int timeout);

        SkinFetcher build();
    }
}
