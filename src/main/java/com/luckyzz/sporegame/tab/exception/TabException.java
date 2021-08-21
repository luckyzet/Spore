package com.luckyzz.sporegame.tab.exception;

import org.jetbrains.annotations.NotNull;

public class TabException extends RuntimeException {

    public TabException(@NotNull final String message) {
        super(message);
    }

}
