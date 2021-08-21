package com.luckyzz.sporegame.user.event;

import com.luckyzz.sporegame.user.SporeUser;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SporeUserEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    protected final SporeUser user;

    protected SporeUserEvent(@NotNull final SporeUser user) {
        this.user = user;
    }

    public @NotNull SporeUser getUser() {
        return user;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

}
