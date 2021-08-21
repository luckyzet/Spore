package com.luckyzz.sporegame.user.statistic;

import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class StatisticEventHandleRegistryImpl implements StatisticEventHandleRegistry {

    public StatisticEventHandleRegistryImpl() {
    }

    @Override
    public void registry(@NotNull final Plugin plugin, @NotNull final SporeUserService userService) {
        new KillsStatisticEventHandle(plugin, userService);
    }

}
