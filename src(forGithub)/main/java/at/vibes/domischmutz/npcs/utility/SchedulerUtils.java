package at.vibes.domischmutz.npcs.utility;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SchedulerUtils {
    private final Plugin plugin;

    public SchedulerUtils(Plugin plugin) {
        this.plugin = plugin;
    }

    public BukkitTask runTaskTimer(BukkitRunnable bukkitRunnable, int delay) {
        return runTaskTimer(bukkitRunnable, delay, delay);
    }

    public BukkitTask runTaskTimer(BukkitRunnable bukkitRunnable, int delay, int continuousDelay) {
        return bukkitRunnable.runTaskTimer(plugin, delay, continuousDelay);
    }

    public BukkitTask runTaskTimerAsynchronously(
            BukkitRunnable runnable, int delay, int continuousDelay) {
        return runnable.runTaskTimerAsynchronously(plugin, delay, continuousDelay);
    }

    public BukkitTask runTaskTimerAsynchronously(
        Runnable runnable, int delay, int continuousDelay) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, continuousDelay);
    }

    public void scheduleSyncDelayedTask(Runnable runnable, int delay) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable, delay);
    }

    public BukkitTask runTask(Runnable runnable) {
        return Bukkit.getScheduler().runTask(plugin, runnable);
    }
}
