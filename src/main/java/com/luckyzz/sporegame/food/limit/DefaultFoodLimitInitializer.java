package com.luckyzz.sporegame.food.limit;

import com.luckyzz.sporegame.food.category.FoodCategories;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

class DefaultFoodLimitInitializer implements FoodLimitInitializer {

    private void put(@NotNull final Map<FoodCategory, FoodLimit> map, @NotNull final FoodLimit limit) {
        map.put(limit.getCategory(), limit);
    }

    @Override
    public @NotNull Map<FoodCategory, FoodLimit> initialize(@NotNull final Map<FoodCategory, FoodLimit> map) {
        put(map, new FoodLimit(FoodCategories.GRASS_FIRST, 200));
        put(map, new FoodLimit(FoodCategories.GRASS_SECOND, 170));
        put(map, new FoodLimit(FoodCategories.GRASS_THIRD, 140));
        put(map, new FoodLimit(FoodCategories.GRASS_FOURTH, 110));
        put(map, new FoodLimit(FoodCategories.GRASS_FIFTH, 80));
        put(map, new FoodLimit(FoodCategories.GRASS_SIXTH, 60));
        put(map, new FoodLimit(FoodCategories.GRASS_SEVENTH, 40));
        put(map, new FoodLimit(FoodCategories.GRASS_EIGHTH, 20));

        put(map, new FoodLimit(FoodCategories.MEAT_FIRST, 100));
        put(map, new FoodLimit(FoodCategories.MEAT_SECOND, 85));
        put(map, new FoodLimit(FoodCategories.MEAT_THIRD, 70));
        put(map, new FoodLimit(FoodCategories.MEAT_FOURTH, 55));
        put(map, new FoodLimit(FoodCategories.MEAT_FIFTH, 40));
        put(map, new FoodLimit(FoodCategories.MEAT_SIXTH, 30));
        put(map, new FoodLimit(FoodCategories.MEAT_SEVENTH, 15));
        put(map, new FoodLimit(FoodCategories.MEAT_EIGHTH, 8));

        return map;
    }

}
