package com.luckyzz.sporegame.admin;

import com.akamecoder.cristalix.command.ChatCommand;
import com.akamecoder.cristalix.command.CommandOptions;
import com.akamecoder.cristalix.command.CommandSession;
import com.akamecoder.cristalix.command.SubCommandList;
import com.luckyzz.sporegame.spawning.SpawningLocationManagement;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

class SpawnCommand extends ChatCommand {

    private final SpawningLocationManagement spawningLocationManagement;

    SpawnCommand(@NotNull final SpawningLocationManagement spawningLocationManagement) {
        this.spawningLocationManagement = spawningLocationManagement;
        this.apply(CommandOptions.subCommandOptions().label("spawn"));
    }

    @Override
    protected void subCommands(@NotNull final SubCommandList list) {
        list.subCommand(new AddSpawnCommand(spawningLocationManagement.getFactory()));
    }

    @Override
    protected void execution(@NotNull final CommandSession session) {
        session.getExecutor().send(
                ChatColor.GRAY + "[" + ChatColor.AQUA + "Spore" + ChatColor.GRAY + "] | Помощь по командам /sporeadmin spawn:\n" +
                ChatColor.AQUA + "/sporeadmin spawn add [Часть мира]" + ChatColor.GRAY + " - добавить точку спавна игрока\n" +
                ChatColor.AQUA + "  | Часть мира (1,2,3,4), также можно указать 0 - это дефолт точка спавна на самом спавне"
        );
    }

}
