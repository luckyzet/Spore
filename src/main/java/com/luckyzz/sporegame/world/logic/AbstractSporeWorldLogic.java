package com.luckyzz.sporegame.world.logic;

import com.luckyzz.sporegame.user.SporeUserService;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSporeWorldLogic implements SporeWorldLogic {

    protected final SporeUserService userService;

    protected AbstractSporeWorldLogic(@NotNull final SporeUserService userService) {
        this.userService = userService;
    }

}
