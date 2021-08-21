package com.luckyzz.sporegame.user.join;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface JoinHandleStrategy {

    void handleUser(@NotNull final SporeUser user);

}
