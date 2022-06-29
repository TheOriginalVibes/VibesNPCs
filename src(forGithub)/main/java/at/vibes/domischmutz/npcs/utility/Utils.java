package at.vibes.domischmutz.npcs.utility;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public final class Utils {
    public static final int BUKKIT_VERSION = NumberUtils.toInt(getFormattedBukkitPackage());
    public static final long SECOND_INTERVAL_NANOS = 1000 * 1000 * 1000L;

    public static boolean PLACEHOLDER_SUPPORT = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");

    public static boolean versionNewer(int version) {
        return BUKKIT_VERSION >= version;
    }

    public static String getBukkitPackage() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static String getFormattedBukkitPackage() {
        final String version = getBukkitPackage().replace("v", "").replace("R", "");
        return version.substring(2, version.length() - 2);
    }

    public static String toColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String randomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < length; index++) {
            stringBuilder.append(ThreadLocalRandom.current().nextInt(0, 9));
        }
        return stringBuilder.toString();
    }

    public static void sendTitle(Player player, String title, String subTitle) {
        player.sendTitle(toColor(title), toColor(subTitle));
    }

    public static void setValue(Object fieldInstance, String fieldName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field f = fieldInstance.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(fieldInstance, value);
    }

    public static Object getValue(Object instance, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field f = instance.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return f.get(instance);
    }

    private Utils() {}
}
