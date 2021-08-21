package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.event.PlayerQuitServerEvent;
import com.akamecoder.cristalix.event.handle.EventHandle;
import com.akamecoder.cristalix.scheduler.SchedulerRunner;
import com.luckyzz.sporegame.character.session.CharacterSessionCleaner;
import com.luckyzz.sporegame.user.event.SporeUserJoinEvent;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

class UserJoinQuitListener extends EventHandle {

    private final SchedulerRunner runner;
    private final SporeUserService userService;
    private final CharacterSessionCleaner sessionCleaner;

    UserJoinQuitListener(@NotNull final Plugin plugin, @NotNull final SchedulerRunner runner, @NotNull final SporeUserService userService, @NotNull final CharacterSessionCleaner sessionCleaner) {
        super(plugin);
        this.runner = runner;
        this.userService = userService;
        this.sessionCleaner = sessionCleaner;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(@NotNull final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        SporeMessages.LOADING_USER.send(player);
        userService.loadUser(player).thenAccept(user -> runner.runTask(() -> {
            final SporeUserJoinEvent joinEvent = new SporeUserJoinEvent(user);
            Bukkit.getPluginManager().callEvent(joinEvent);
            SporeMessages.LOADED_USER.send(player);
        }));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(@NotNull final PlayerQuitServerEvent event) {
        final SporeUser user = userService.getUser(event.getPlayer());
        user.getCharacterSession().ifPresent(session -> sessionCleaner.clearSession(user));
        userService.unloadUser(event.getPlayer());
    }

}
