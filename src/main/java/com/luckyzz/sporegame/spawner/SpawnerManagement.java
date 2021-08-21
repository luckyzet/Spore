package com.luckyzz.sporegame.spawner;

import com.akamecoder.cristalix.api.Cancelable;
import com.akamecoder.cristalix.scheduler.Scheduler;
import com.akamecoder.cristalix.scheduler.SchedulerRunner;
import com.luckyzz.sporegame.food.SporeFoodProcessor;
import com.luckyzz.sporegame.util.Log;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public final class SpawnerManagement implements Cancelable {

    private final SpawnerRepository repository;
    private final SpawnerFactory factory;
    private final Scheduler spawnerTask;

    @SuppressWarnings("All")
    public SpawnerManagement(@NotNull final Plugin plugin, @NotNull final SchedulerRunner runner, @NotNull final SporeFoodProcessor foodProcessor) {
        final File folder = new File(plugin.getDataFolder(), "storage");
        if(!folder.exists()) {
            folder.mkdirs();
        }
        final File file = new File(folder, "spawners.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (final IOException exception) {
                Log.warn("Error was occurred while creating spawners storage!");
                exception.printStackTrace();
            }
        }

        final SpawnerMap spawnerMap = new SpawnerMap();
        repository = new YamlSpawnerRepository(foodProcessor, spawnerMap, runner, file);
        factory = new SpawnerFactoryImpl(spawnerMap, repository, foodProcessor);

        spawnerTask = new RegularSpawnerTask(plugin, spawnerMap);
    }

    public @NotNull SpawnerFactory getFactory() {
        return factory;
    }

    @Override
    public void cancel() {
        repository.cancel();
        spawnerTask.cancel();
    }

}
