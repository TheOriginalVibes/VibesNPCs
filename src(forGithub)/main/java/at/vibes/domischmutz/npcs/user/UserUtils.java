package at.vibes.domischmutz.npcs.user;

public final class UserUtils {

    public static void sendPackets(Iterable<User> users, Object... packets) {
        for (User user : users) {
            for (Object packet : packets) {
                try {
                    user.sendPackets(packet);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private UserUtils() {}
}
