package com.luckyzz.sporegame.food.limit;

import com.luckyzz.sporegame.food.category.FoodCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface FoodLimitInitializer {

    @NotNull Map<FoodCategory, FoodLimit> initialize(@NotNull final Map<FoodCategory, FoodLimit> map);

}
