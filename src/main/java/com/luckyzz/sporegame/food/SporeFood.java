package com.luckyzz.sporegame.food;

import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface SporeFood {

    default @NotNull FoodCategory getCategory() {
        return getSpecifications().getCategory();
    }

    @NotNull FoodSpecifications getSpecifications();

    @NotNull LivingEntity getEntity();

    default boolean isDead() {
        return getEntity().isDead();
    }

}
