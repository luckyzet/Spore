package com.luckyzz.sporegame.food.limit;

import com.akamecoder.cristalix.api.Typable;
import com.luckyzz.sporegame.food.FoodType;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.jetbrains.annotations.NotNull;

public class FoodLimit implements Typable<FoodType> {

    private final FoodCategory category;
    private final long limit;

    public FoodLimit(@NotNull final FoodCategory category, final long limit) {
        this.category = category;
        this.limit = limit;
    }

    public @NotNull FoodCategory getCategory() {
        return category;
    }

    @Override
    public @NotNull FoodType getType() {
        return category.getType();
    }

    public int getLevel() {
        return category.getLevel();
    }

    public long getLimit() {
        return limit;
    }

}
