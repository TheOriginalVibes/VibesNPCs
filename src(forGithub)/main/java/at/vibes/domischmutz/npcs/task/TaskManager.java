package at.vibes.domischmutz.npcs.task;

import at.vibes.domischmutz.npcs.task.internal.AsyncBukkitTaskManager;

public interface TaskManager {
    static TaskManager of(int time) {
        return new AsyncBukkitTaskManager(time);
    }

    void start();

    Task submitTask(Task task);
    Task removeTask(Task task);

    Iterable<Task> getTasks();

    boolean isStarted();
    boolean isRunning();
    boolean isCanceled();
}
