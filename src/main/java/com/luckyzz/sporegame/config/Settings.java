package com.luckyzz.sporegame.config;

import com.akamecoder.cristalix.api.Pathable;
import org.jetbrains.annotations.NotNull;

public enum Settings implements Pathable {

    CHARACTERS_COUNT("settings.charactersCount");

    private final String path;

    Settings(@NotNull final String path) {
        this.path = path;
    }

    @Override
    public @NotNull String getPath() {
        return path;
    }

}
