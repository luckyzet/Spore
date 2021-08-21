package com.luckyzz.sporegame.character.session;

import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public interface CharacterSessionCleaner {

    @NotNull DetailedSporeCharacter clearSession(@NotNull final SporeUser user);

}
