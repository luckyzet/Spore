package com.luckyzz.sporegame.user;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface SporeUserService {

    @NotNull CompletableFuture<SporeUser> loadUser(@NotNull final String name);

    default @NotNull CompletableFuture<SporeUser> loadUser(@NotNull final Player player) {
        return loadUser(player.getName());
    }

    void unloadUser(@NotNull final String name);

    default void unloadUser(@NotNull final Player player) {
        unloadUser(player.getName());
    }

    @NotNull SporeUser getUser(@NotNull final String name);

    default @NotNull SporeUser getUser(@NotNull final Player player) {
        return getUser(player.getName());
    }

}
