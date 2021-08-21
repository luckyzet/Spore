package com.luckyzz.sporegame.chat.exception;

import org.jetbrains.annotations.NotNull;

public class ChatException extends RuntimeException {

    public ChatException(@NotNull final String message) {
        super(message);
    }

}
