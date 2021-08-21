package com.luckyzz.sporegame.spawner;

import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface SpawnerFactory {

    @NotNull RandomDelayedSpawner createRandomDelayed(@NotNull final String name, @NotNull final Location location,
                                                      @NotNull final FoodCategory category, final int minDelay, final int maxDelay,
                                                      final int distance);

}
