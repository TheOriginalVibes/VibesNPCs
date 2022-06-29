package at.vibes.domischmutz.npcs.npc.function;

public interface NpcFunctionValue<T> {
    static <T> NpcFunctionValue<T> of(Class<T> typeClass, String name) {
        return new DefaultValue<>(name, typeClass);
    }

    String name();
    Class<T> getType();

    final class DefaultValue<T> implements NpcFunctionValue<T> {
        private final String name;
        private final Class<T> type;

        public DefaultValue(String name, Class<T> type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public Class<T> getType() {
            return type;
        }
    }
}
