package com.luckyzz.sporegame.spawning;

import com.akamecoder.cristalix.api.Cancelable;
import com.akamecoder.cristalix.scheduler.SchedulerRunner;
import com.luckyzz.sporegame.config.SettingConfig;
import com.luckyzz.sporegame.util.Log;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public final class SpawningLocationManagement implements Cancelable {

    private final SpawningLocationRepository repository;
    private final SpawningLocationSelector selector;
    private final SpawningLocationFactory factory;

    @SuppressWarnings("All")
    public SpawningLocationManagement(@NotNull final Plugin plugin, @NotNull final SettingConfig config, @NotNull final SchedulerRunner runner) {
        final File folder = new File(plugin.getDataFolder(), "storage");
        if(!folder.exists()) {
            folder.mkdirs();
        }
        final File file = new File(folder, "spawnings.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (final IOException exception) {
                Log.warn("Error was occurred while creating spawning location storage!");
                exception.printStackTrace();
            }
        }

        final SpawningLocationStorage storage = new SpawningLocationStorage();
        repository = new YamlSpawningLocationRepository(storage, runner, file);
        selector = new RandomlySpawningLocationSelector(config, storage);
        factory = new SpawningLocationFactoryImpl(storage, repository);
    }

    public @NotNull SpawningLocationSelector getSelector() {
        return selector;
    }

    public @NotNull SpawningLocationFactory getFactory() {
        return factory;
    }

    @Override
    public void cancel() {
        repository.cancel();
    }

}
