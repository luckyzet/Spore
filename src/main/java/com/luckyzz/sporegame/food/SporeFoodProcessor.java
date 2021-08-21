package com.luckyzz.sporegame.food;

import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface SporeFoodProcessor {

    @NotNull SporeFood spawn(@NotNull final FoodCategory category, @NotNull final Location location);

}
