package com.luckyzz.sporegame.character.session;

import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface CharacterSessionFactory {

    @NotNull SporeCharacterSession createSession(@NotNull final DetailedSporeCharacter character, @NotNull final Consumer<SporeCharacterSession> consumer);

    default @NotNull SporeCharacterSession createSession(@NotNull final DetailedSporeCharacter character) {
        return createSession(character, session -> {});
    }

}
