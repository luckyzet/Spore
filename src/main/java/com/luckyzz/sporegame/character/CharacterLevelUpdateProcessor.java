package com.luckyzz.sporegame.character;

import com.luckyzz.sporegame.config.ConfigurationCharacterLevel;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.jetbrains.annotations.NotNull;

public interface CharacterLevelUpdateProcessor {

    void update(@NotNull final SporeCharacterSession session, @NotNull final ConfigurationCharacterLevel next);

}
