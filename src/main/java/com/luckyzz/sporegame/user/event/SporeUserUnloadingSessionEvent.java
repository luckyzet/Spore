package com.luckyzz.sporegame.user.event;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public class SporeUserUnloadingSessionEvent extends SporeUserSessionEvent {

    public SporeUserUnloadingSessionEvent(@NotNull final SporeUser user) {
        super(user);
    }

}
