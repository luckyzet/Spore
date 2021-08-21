package com.luckyzz.sporegame.spawning.exception;

import org.jetbrains.annotations.NotNull;

public class SpawningException extends RuntimeException {

    public SpawningException(@NotNull final String message) {
        super(message);
    }

}
