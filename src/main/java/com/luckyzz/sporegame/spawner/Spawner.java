package com.luckyzz.sporegame.spawner;

import com.akamecoder.cristalix.api.Typable;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface Spawner extends Typable<SpawnerType> {

    @NotNull String getName();

    @NotNull FoodCategory getCategory();

    @NotNull Location getLocation();

    boolean doTick();

}
