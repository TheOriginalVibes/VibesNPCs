package at.vibes.domischmutz.npcs.skin;

import at.vibes.domischmutz.npcs.skin.internal.DefaultSkinFetcherResultBuilder;

import java.io.Reader;

public interface SkinFetcherResult {
    static SkinFetcherResultBuilder builder() {
        return new DefaultSkinFetcherResultBuilder();
    }

    static SkinFetcherResult of(String texture, String signature) {
        return builder()
            .withTexture(texture)
            .withSignature(signature)
            .build();
    }

    String getTexture();
    String getSignature();

    interface SkinFetcherResultBuilder {

        SkinFetcherResultBuilder withTexture(String texture);
        SkinFetcherResultBuilder withSignature(String signature);

        SkinFetcherResult build();
    }
}
