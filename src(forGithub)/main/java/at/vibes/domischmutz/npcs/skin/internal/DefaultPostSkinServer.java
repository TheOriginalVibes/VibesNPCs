package at.vibes.domischmutz.npcs.skin.internal;

import com.google.gson.JsonObject;
import at.vibes.domischmutz.npcs.skin.SkinFetcherResult;
import at.vibes.domischmutz.npcs.http.HttpMethod;
import at.vibes.domischmutz.npcs.skin.SkinFetcherService;

import java.io.Reader;

public class DefaultPostSkinServer implements SkinFetcherService {

    public static SkinFetcherService INSTANCE = new DefaultPostSkinServer();

    @Override
    public String getUrl() {
        return "https://api.mineskin.org/generate/url";
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public SkinFetcherResult parse(Reader reader) {
        JsonObject properties = JSON_PARSER.parse(reader)
            .getAsJsonObject()
            .getAsJsonObject("data").getAsJsonObject("textures");
        return SkinFetcherResult.of(
            properties.get("value").getAsString(),
            properties.get("signature").getAsString());
    }
}
