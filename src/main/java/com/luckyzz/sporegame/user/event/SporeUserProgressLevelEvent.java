package com.luckyzz.sporegame.user.event;

import com.luckyzz.sporegame.character.level.CharacterLevel;
import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public class SporeUserProgressLevelEvent extends SporeUserSessionEvent {

    public SporeUserProgressLevelEvent(@NotNull final SporeUser user) {
        super(user);
    }

    public @NotNull CharacterLevel getLevel() {
        return getSession().getDetailedLevel();
    }

}
