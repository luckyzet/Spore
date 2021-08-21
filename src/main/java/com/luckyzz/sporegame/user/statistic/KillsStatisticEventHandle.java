package com.luckyzz.sporegame.user.statistic;

import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class KillsStatisticEventHandle extends StatisticEventHandle {

    KillsStatisticEventHandle(@NotNull final Plugin plugin, @NotNull final SporeUserService userService) {
        super(plugin, userService);
    }

    @EventHandler
    public void onDeath(@NotNull final PlayerDeathEvent event) {
        final Player killer = event.getEntity().getKiller();
        if(killer == null) {
            return;
        }
        userService.getUser(killer).getStatistic().incrementStatistic(Statistics.KILLS);
    }

}
