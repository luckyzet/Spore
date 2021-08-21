package com.luckyzz.sporegame.config.exception;

import org.jetbrains.annotations.NotNull;

public class ConfigParseException extends RuntimeException {

    public ConfigParseException(@NotNull final String message) {
        super(message);
    }

}
