package com.luckyzz.sporegame.user.event;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public class SporeUserJoinEvent extends SporeUserEvent {

    public SporeUserJoinEvent(@NotNull final SporeUser user) {
        super(user);
    }

}
