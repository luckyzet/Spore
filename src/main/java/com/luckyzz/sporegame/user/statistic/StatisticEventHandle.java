package com.luckyzz.sporegame.user.statistic;

import com.akamecoder.cristalix.event.handle.EventHandle;
import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class StatisticEventHandle extends EventHandle {

    protected final SporeUserService userService;

    protected StatisticEventHandle(@NotNull final Plugin plugin, @NotNull final SporeUserService userService) {
        super(plugin);
        this.userService = userService;
    }

}
