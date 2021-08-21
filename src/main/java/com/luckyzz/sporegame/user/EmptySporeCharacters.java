package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.character.SporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class EmptySporeCharacters implements SporeCharacters {

    private final String name;

    EmptySporeCharacters(@NotNull final String name) {
        this.name = name;
    }

    @Override
    public @NotNull SporeUser getUser() {
        return new EmptySporeUser(name);
    }

    @Override
    public int getMaximumCharacterCount() {
        return 7;
    }

    @Override
    public int incrementMaximumCharacterCount(@NotNull final SporeUserRepository.CharacterRepository repository) {
        return 0;
    }

    @Override
    public int getCharacterCount() {
        return 7;
    }

    @Override
    public @NotNull AkameOptional<SporeCharacter> getCharacter(final int index) {
        return AkameOptional.empty();
    }

    @Override
    public @NotNull AkameOptional<SporeCharacterSession> getSession() {
        return AkameOptional.empty();
    }

    @Override
    public @NotNull Collection<SporeCharacter> getCharacters() {
        return new ArrayList<>();
    }

    @Override
    public void addCharacter(@NotNull final SporeCharacter character) {
    }
}
