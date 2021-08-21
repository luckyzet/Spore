package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.event.handle.EventHandle;
import com.luckyzz.sporegame.user.event.SporeUserSessionEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

final class WorldChangingListener extends EventHandle {

    private final SporeUserRepository repository;

    WorldChangingListener(@NotNull final Plugin plugin, @NotNull final SporeUserRepository repository) {
        super(plugin);
        this.repository = repository;
    }

    @EventHandler
    public void onSession(@NotNull final SporeUserSessionEvent event) {
        repository.saveWorld(event.getUser());
    }

}
