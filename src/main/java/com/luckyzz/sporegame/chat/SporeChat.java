package com.luckyzz.sporegame.chat;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public interface SporeChat {

    @NotNull Predicate<SporeUser> getPredicate();

    @NotNull ChatMessage format(@NotNull final SporeUser user, @NotNull final String message);

}
