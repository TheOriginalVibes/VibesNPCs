package at.vibes.domischmutz.npcs.skin.internal;

import com.google.gson.JsonObject;
import at.vibes.domischmutz.npcs.http.HttpMethod;
import at.vibes.domischmutz.npcs.skin.SkinFetcherResult;
import at.vibes.domischmutz.npcs.skin.SkinFetcherService;

import java.io.Reader;

public class DefaultGetSkinServer implements SkinFetcherService {

    public static SkinFetcherService INSTANCE = new DefaultGetSkinServer();

    @Override
    public String getUrl() {
        return "https://api.ashcon.app/mojang/v2/user";
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public SkinFetcherResult parse(Reader reader) {
        JsonObject properties = JSON_PARSER.parse(reader)
            .getAsJsonObject()
            .getAsJsonObject("textures").getAsJsonObject("raw");
        return SkinFetcherResult.of(
            properties.get("value").getAsString(),
            properties.get("signature").getAsString());
    }
}
