package com.luckyzz.sporegame.user.event;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public class SporeUserLoadingSessionEvent extends SporeUserSessionEvent {

    public SporeUserLoadingSessionEvent(@NotNull final SporeUser user) {
        super(user);
    }

}
