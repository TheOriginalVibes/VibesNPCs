package at.vibes.domischmutz.npcs.user.internal;

import at.vibes.domischmutz.npcs.user.User;
import at.vibes.domischmutz.npcs.user.UserStore;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultUserStore implements UserStore {
    private final Map<UUID, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User getUser(UUID uuid) {
        return userMap.get(uuid);
    }

    @Override
    public User addUser(User user) {
        return userMap.computeIfAbsent(user.getUUID(), (u) -> user);
    }

    @Override
    public void removeUser(UUID uuid) {
        userMap.remove(uuid);
    }

    @Override
    public Iterable<User> getUsers() {
        return userMap.values();
    }
}
