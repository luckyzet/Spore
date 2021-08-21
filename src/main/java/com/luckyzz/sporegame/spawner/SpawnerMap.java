package com.luckyzz.sporegame.spawner;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class SpawnerMap implements Iterable<Spawner> {

    private final Map<String, Spawner> spawners;

    SpawnerMap(@NotNull final Map<String, Spawner> spawners) {
        this.spawners = spawners;
    }

    SpawnerMap() {
        this(new HashMap<>());
    }

    @Override
    public @NotNull Iterator<Spawner> iterator() {
        return spawners.values().iterator();
    }

    public @Nullable Spawner getSpawner(@NotNull final String name) {
        return spawners.get(name);
    }

    public @Nullable Spawner removeSpawner(@NotNull final String name) {
        return spawners.remove(name);
    }

    public @Nullable Spawner removeSpawner(@NotNull final Spawner spawner) {
        return removeSpawner(spawner.getName());
    }

    public @Nullable Spawner putSpawner(@NotNull final Spawner spawner) {
        return spawners.put(spawner.getName(), spawner);
    }

}
