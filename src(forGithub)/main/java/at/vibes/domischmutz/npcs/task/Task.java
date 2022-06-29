package at.vibes.domischmutz.npcs.task;

import at.vibes.domischmutz.npcs.task.internal.CancelableTask;
import at.vibes.domischmutz.npcs.task.internal.DefaultTask;

import java.util.concurrent.Callable;

public interface Task extends Runnable {
    static Task of(Runnable runnable) {
        return new DefaultTask(runnable);
    }

    static Task of(Runnable runnable, Callable<Boolean> callable) {
        return new CancelableTask(runnable, callable);
    }

    @Override
    void run();

    boolean cancel();
    boolean isCanceled();
}
