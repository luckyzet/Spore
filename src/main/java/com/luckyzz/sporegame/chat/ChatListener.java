package com.luckyzz.sporegame.chat;

import com.akamecoder.cristalix.event.handle.EventHandle;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

class ChatListener extends EventHandle {

    private final SporeChatStorage chatStorage;
    private final SporeUserService userService;

    ChatListener(@NotNull final Plugin plugin, @NotNull final SporeChatStorage chatStorage, @NotNull final SporeUserService userService) {
        super(plugin);
        this.chatStorage = chatStorage;
        this.userService = userService;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onChat(@NotNull final AsyncPlayerChatEvent event) {
        final SporeUser user = userService.getUser(event.getPlayer());

        final SporeChat chat = chatStorage.getChat(user);
        chat.format(user, event.getMessage()).apply();

        event.setCancelled(true);
    }

}
