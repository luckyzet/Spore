package com.luckyzz.sporegame.user.event;

import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.character.exception.CharacterException;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.jetbrains.annotations.NotNull;

public class SporeUserSessionEvent extends SporeUserEvent {

    protected SporeUserSessionEvent(@NotNull final SporeUser user) {
        super(user);
    }
    
    public @NotNull SporeCharacterSession getSession() {
        return user.getCharacterSession().orElseThrow(() -> {
            return new CharacterException("The session is null!");
        });
    }

}
