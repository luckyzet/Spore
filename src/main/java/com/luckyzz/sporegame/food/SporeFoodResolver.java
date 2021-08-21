package com.luckyzz.sporegame.food;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface SporeFoodResolver {

    @NotNull AkameOptional<SporeFood> resolve(@NotNull final Entity entity);

    @NotNull Collection<SporeFood> resolve(@NotNull final FoodCategory category);

}
