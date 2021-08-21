package com.luckyzz.sporegame.food.category;

import com.akamecoder.cristalix.api.Typable;
import com.luckyzz.sporegame.food.FoodType;
import org.jetbrains.annotations.NotNull;

public class FoodCategory implements Typable<FoodType> {

    private final FoodType type;
    private final int level;

    public FoodCategory(@NotNull final FoodType type, final int level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public @NotNull FoodType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

}
