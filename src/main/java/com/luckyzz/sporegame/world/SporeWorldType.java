package com.luckyzz.sporegame.world;

import com.akamecoder.cristalix.util.Enums;
import org.jetbrains.annotations.NotNull;

public enum SporeWorldType {

    SPAWN,
    ARENA;

    public static @NotNull SporeWorldType fromString(@NotNull final String string) {
        return Enums.of(values(), string);
    }

}
