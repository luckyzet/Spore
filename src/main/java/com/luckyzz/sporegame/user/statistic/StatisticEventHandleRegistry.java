package com.luckyzz.sporegame.user.statistic;

import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface StatisticEventHandleRegistry {

    void registry(@NotNull final Plugin plugin, @NotNull final SporeUserService userService);

}
