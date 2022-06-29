package at.vibes.domischmutz.npcs.task.internal;

import at.vibes.domischmutz.npcs.task.Task;

public class DefaultTask implements Task {
    private final Runnable runnable;

    private boolean cancel;

    public DefaultTask(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }

    @Override
    public boolean cancel() {
        return this.cancel=!cancel;
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }
}
