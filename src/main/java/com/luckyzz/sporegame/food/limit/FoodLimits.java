package com.luckyzz.sporegame.food.limit;

import com.luckyzz.sporegame.food.category.FoodCategory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class FoodLimits {

    private static final Map<FoodCategory, FoodLimit> limits = new DefaultFoodLimitInitializer().initialize(new HashMap<>());

    private FoodLimits() {
        throw new UnsupportedOperationException();
    }

    public static @NotNull FoodLimit getLimit(@NotNull final FoodCategory category) {
        return limits.get(category);
    }

}
