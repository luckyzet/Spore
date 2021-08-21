package com.luckyzz.sporegame.food;

import com.akamecoder.cristalix.util.Enums;
import org.jetbrains.annotations.NotNull;

public enum FoodType {

    MEAT,
    GRASS,
    PIECE;

    public static @NotNull FoodType fromString(@NotNull final String string) {
        return Enums.of(values(), string);
    }

}
