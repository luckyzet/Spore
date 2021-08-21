package com.luckyzz.sporegame.spawning;

import com.luckyzz.sporegame.world.SpawningPart;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

class SpawningLocationFactoryImpl implements SpawningLocationFactory {

    private final SpawningLocationStorage storage;
    private final SpawningLocationRepository repository;

    SpawningLocationFactoryImpl(@NotNull final SpawningLocationStorage storage, @NotNull final SpawningLocationRepository repository) {
        this.storage = storage;
        this.repository = repository;
    }

    @Override
    public @NotNull SpawningLocation createSpawningLocation(@NotNull final SpawningPart part, @NotNull final Location location) {
        final SpawningLocation spawningLocation = new SpawningLocationImpl(part, location);
        storage.put(spawningLocation);
        repository.saveSpawningLocation(spawningLocation);
        return spawningLocation;
    }

}
