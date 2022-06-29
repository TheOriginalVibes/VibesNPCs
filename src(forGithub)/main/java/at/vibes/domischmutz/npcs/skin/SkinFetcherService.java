package at.vibes.domischmutz.npcs.skin;

import at.vibes.domischmutz.npcs.skin.internal.DefaultGetSkinServer;
import at.vibes.domischmutz.npcs.skin.internal.DefaultPostSkinServer;
import com.google.gson.JsonParser;
import at.vibes.domischmutz.npcs.http.HttpMethod;

import java.io.Reader;

public interface SkinFetcherService {
    static SkinFetcherService of(HttpMethod httpMethod) {
        if (httpMethod == HttpMethod.GET) {
            return DefaultGetSkinServer.INSTANCE;
        } else if (httpMethod == HttpMethod.POST) {
            return DefaultPostSkinServer.INSTANCE;
        } else {
            throw new IllegalStateException(
                String.format("no default skin service found for http method: %s", httpMethod.name()));
        }
    }

    JsonParser JSON_PARSER = new JsonParser();
    String getUrl();
    HttpMethod getMethod();
    SkinFetcherResult parse(Reader reader);
}
