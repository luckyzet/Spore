package com.luckyzz.sporegame.user.exception;

import org.jetbrains.annotations.NotNull;

public class UserException extends RuntimeException {

    public UserException(@NotNull final String message) {
        super(message);
    }
}

