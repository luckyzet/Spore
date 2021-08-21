package com.luckyzz.sporegame.spawner;

import com.akamecoder.cristalix.scheduler.Scheduler;
import com.akamecoder.cristalix.scheduler.SchedulerTicks;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class RegularSpawnerTask extends Scheduler {

    private final SpawnerMap spawnerMap;

    RegularSpawnerTask(@NotNull final Plugin plugin, @NotNull final SpawnerMap spawnerMap) {
        super(plugin);
        this.spawnerMap = spawnerMap;
        this.runTaskTimer(SchedulerTicks.toTicks(1, TimeUnit.SECONDS));
    }

    @Override
    public void run() {
        spawnerMap.forEach(Spawner::doTick);
    }

}
