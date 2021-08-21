package com.luckyzz.sporegame.spawner;

import com.luckyzz.sporegame.food.SporeFoodProcessor;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

class SpawnerFactoryImpl implements SpawnerFactory {

    private final SpawnerMap spawnerMap;
    private final SpawnerRepository repository;
    private final SporeFoodProcessor foodProcessor;

    SpawnerFactoryImpl(@NotNull final SpawnerMap spawnerMap, @NotNull final SpawnerRepository repository, @NotNull final SporeFoodProcessor foodProcessor) {
        this.spawnerMap = spawnerMap;
        this.repository = repository;
        this.foodProcessor = foodProcessor;
    }

    private <T extends Spawner> @NotNull T saveSpawner(@NotNull final T spawner) {
        spawnerMap.putSpawner(spawner);
        repository.saveSpawner(spawner);
        return spawner;
    }

    @Override
    public @NotNull RandomDelayedSpawner createRandomDelayed(@NotNull final String name, @NotNull final Location location,
                                                             @NotNull final FoodCategory category, final int minDelay, final int maxDelay,
                                                             final int distance) {
        return saveSpawner(new RandomDelayedSpawner(foodProcessor, name, category, location, minDelay, maxDelay, distance));
    }

}
