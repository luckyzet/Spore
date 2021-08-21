package com.luckyzz.sporegame.user.event;

import com.luckyzz.sporegame.character.level.CharacterLevel;
import com.luckyzz.sporegame.config.ConfigurationCharacterLevel;
import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public class SporeUserLevelUpEvent extends SporeUserSessionEvent {

    public SporeUserLevelUpEvent(@NotNull final SporeUser user) {
        super(user);
    }

    public @NotNull CharacterLevel getNowLevel() {
        return getSession().getDetailedLevel();
    }

    public @NotNull ConfigurationCharacterLevel getNextLevel() {
        return getSession().getDetailedLevel().getNextLevel().get();
    }

}
