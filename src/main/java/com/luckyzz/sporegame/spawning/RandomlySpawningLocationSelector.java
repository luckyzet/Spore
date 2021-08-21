package com.luckyzz.sporegame.spawning;

import com.luckyzz.sporegame.spawning.exception.SpawningException;
import com.luckyzz.sporegame.world.SpawningPart;
import com.luckyzz.sporegame.config.SettingConfig;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

class RandomlySpawningLocationSelector implements SpawningLocationSelector {

    private final SettingConfig config;
    private final SpawningLocationStorage storage;

    RandomlySpawningLocationSelector(@NotNull final SettingConfig config, @NotNull final SpawningLocationStorage storage) {
        this.config = config;
        this.storage = storage;
    }

    @Override
    public @NotNull SpawningLocation selectLocation(@NotNull final SpawningPart part) {
        final Collection<SpawningLocation> locations = storage.getPart(part);
        if(locations.size() == 0) {
            new SpawningException("Random location selector has not selected location correctly!").printStackTrace();
            return new SpawningLocationImpl(part, new Location(config.getWorld(part.getWorldType()).getWorld(), 100, 100, 100));
        }

        if(locations.size() == 1) {
            return locations.stream().findFirst().orElseGet(() -> {
                new SpawningException("Random location selector has not selected location correctly!").printStackTrace();
                return new SpawningLocationImpl(part, new Location(config.getWorld(part.getWorldType()).getWorld(), 100, 100, 100));
            });
        }

        return storage.getPart(part).stream().skip(ThreadLocalRandom.current().nextInt(storage.size() - 1)).findFirst().orElseGet(() -> {
            new SpawningException("Random location selector has not selected location correctly!").printStackTrace();
            return new SpawningLocationImpl(part, new Location(config.getWorld(part.getWorldType()).getWorld(), 100, 100, 100));
        });
    }

}
