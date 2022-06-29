package at.vibes.domischmutz.npcs.replacer.internal;

import at.vibes.domischmutz.npcs.replacer.StringReplacer;
import at.vibes.domischmutz.npcs.utility.Utils;
import net.md_5.bungee.api.ChatColor;

public class RGBLine implements StringReplacer {
    private static final char HEX_SYMBOL = '#';
    private static final int MAX_HEX_LENGTH = 6;

    @Override
    public String apply(String string) {
        String rgbString = string;
        for (int i = 0; i < rgbString.length(); i++) {
            if (rgbString.charAt(i) == HEX_SYMBOL) {
                final int endIndex = i + MAX_HEX_LENGTH + 1;
                final StringBuilder stringBuilder = new StringBuilder();
                boolean success = true;
                for (int i2 = i; i2 < endIndex; i2++) {
                    if (rgbString.length() - 1 < i2) {
                        success = false;
                        break;
                    }
                    stringBuilder.append(rgbString.charAt(i2));
                }
                if (success) {
                    try {
                        rgbString = rgbString.substring(0, i)
                            + ChatColor.of(stringBuilder.toString())
                            + rgbString.substring(endIndex);
                    } catch (Exception e) {
                        //
                    }
                }
            }
        }
        return rgbString;
    }

    @Override
    public boolean isSupported() {
        return Utils.BUKKIT_VERSION > 15;
    }
}
