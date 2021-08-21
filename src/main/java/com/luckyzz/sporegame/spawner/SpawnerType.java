package com.luckyzz.sporegame.spawner;

import com.akamecoder.cristalix.util.Enums;
import org.jetbrains.annotations.NotNull;

public enum SpawnerType {

    RANDOM_DELAYED;

    public static @NotNull SpawnerType fromString(@NotNull final String string) {
        return Enums.of(values(), string);
    }

}
