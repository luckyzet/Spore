package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.world.EmptySporeWorld;
import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.statistic.SporeUserStatistic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EmptySporeUser implements SporeUser {

    private final String name;

    EmptySporeUser(@NotNull final String name) {
        this.name = name;
    }

    @Override
    public @NotNull SporeWorld getWorld() {
        return new EmptySporeWorld();
    }

    @Override
    public void setWorld(@NotNull final SporeWorld world) {
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return UUID.randomUUID();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull SporeCharacters getCharacters() {
        return new EmptySporeCharacters(name);
    }

    @Override
    public @NotNull SporeUserStatistic getStatistic() {
        return new EmptySporeUserStatistic();
    }

    @Override
    public @NotNull AkameOptional<SporeCharacterSession> getCharacterSession() {
        return AkameOptional.empty();
    }

    @Override
    public void setCharacterSession(@Nullable final SporeCharacterSession characterSession) {
    }
}
