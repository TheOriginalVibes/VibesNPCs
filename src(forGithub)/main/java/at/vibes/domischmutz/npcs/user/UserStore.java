package at.vibes.domischmutz.npcs.user;

import at.vibes.domischmutz.npcs.user.internal.DefaultUserStore;

import java.util.UUID;

public interface UserStore {
    static UserStore of() {
        return new DefaultUserStore();
    }


    User getUser(UUID uuid);
    User addUser(User user);

    void removeUser(UUID uuid);

    Iterable<User> getUsers();
}
