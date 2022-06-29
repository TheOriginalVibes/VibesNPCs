package at.vibes.domischmutz.npcs.user;

import at.vibes.domischmutz.npcs.user.internal.ReflectivePluginUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface User {
    static User of(Player player) throws Exception {
        return new ReflectivePluginUser(player);
    }

    UUID getUUID();
    Location getLocation();

    void sendPackets(Object... packets) throws Exception;

    default Player getPlayer() {
        return Bukkit.getPlayer(getUUID());
    }
}
