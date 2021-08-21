package com.luckyzz.sporegame.character.exception;

import org.jetbrains.annotations.NotNull;

public class CharacterException extends RuntimeException {

    public CharacterException(@NotNull final String message) {
        super(message);
    }
}
