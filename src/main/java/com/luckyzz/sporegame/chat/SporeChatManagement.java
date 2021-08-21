package com.luckyzz.sporegame.chat;

import com.akamecoder.cristalix.api.Cancelable;
import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class SporeChatManagement implements Cancelable {

    public SporeChatManagement(@NotNull final Plugin plugin, @NotNull final SporeUserService userService) {
        final SporeChatStorage chatStorage = new SporeChatStorage();
        chatStorage.add(new SpawnSporeChat());
        chatStorage.add(new ArenaSporeChat());

        new ChatListener(plugin, chatStorage, userService);
    }

    @Override
    public void cancel() {

    }

}
