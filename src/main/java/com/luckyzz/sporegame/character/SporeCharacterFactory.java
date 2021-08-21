package com.luckyzz.sporegame.character;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public interface SporeCharacterFactory {

    @NotNull DetailedSporeCharacter createCharacter(@NotNull final SporeUser user, @NotNull final CharacterDirection direction, @NotNull final String name);

}
