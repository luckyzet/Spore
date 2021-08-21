package com.luckyzz.sporegame.food;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

class SporeFoodResolverImpl implements SporeFoodResolver {

    private final FoodMap foodMap;

    SporeFoodResolverImpl(@NotNull final FoodMap foodMap) {
        this.foodMap = foodMap;
    }

    @Override
    public @NotNull AkameOptional<SporeFood> resolve(@NotNull final Entity entity) {
        return AkameOptional.ofOptional(foodMap.stream().filter(food -> food.getEntity().equals(entity)).findFirst());
    }

    @Override
    public @NotNull Collection<SporeFood> resolve(@NotNull final FoodCategory category) {
        return foodMap.getMap().get(category);
    }

}
