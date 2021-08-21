package com.luckyzz.sporegame.admin;

import com.akamecoder.cristalix.command.*;
import com.akamecoder.cristalix.command.precondition.ChatCommandPreconditions;
import com.luckyzz.sporegame.spawner.SpawnerManagement;
import com.luckyzz.sporegame.spawning.SpawningLocationManagement;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import com.luckyzz.sporegame.util.constant.SporePermissions;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class SporeAdminCommand extends ChatCommand {

    private final SpawningLocationManagement spawningLocationManagement;
    private final SpawnerManagement spawnerManagement;

    public SporeAdminCommand(@NotNull final SpawningLocationManagement spawningLocationManagement, @NotNull final SpawnerManagement spawnerManagement) {
        this.spawningLocationManagement = spawningLocationManagement;
        this.spawnerManagement = spawnerManagement;
        this.apply(CommandOptions.commandOptions().label("sporeadmin").aliases("admin", "spore"));
    }

    @Override
    protected void preconditions(@NotNull final PreconditionList list) {
        list.precondition(ChatCommandPreconditions.executorPlayer().action(session -> {
            session.getExecutor().send(SporeMessages.FAILED_ONLY_PLAYER);
        }));

        list.precondition(ChatCommandPreconditions.permission(SporePermissions.ADMIN).action(session -> {
            session.getExecutor().send(SporeMessages.FAILED_PERMISSION);
        }));
    }

    @Override
    protected void subCommands(@NotNull final SubCommandList list) {
        list.subCommand(new SpawnCommand(spawningLocationManagement));
        list.subCommand(new SpawnerCommand(spawnerManagement));
    }

    @Override
    protected void execution(@NotNull final CommandSession session) {
        session.getExecutor().send(
                ChatColor.GRAY + "[" + ChatColor.AQUA + "Spore" + ChatColor.GRAY + "] | Помощь по командам:\n" +
                ChatColor.AQUA + "/sporeadmin spawn " + ChatColor.GRAY + "- настройка точек спавна игроков\n" +
                ChatColor.AQUA + "/sporeadmin spawner " + ChatColor.GRAY + "- настройка спавнеров"
        );
    }

}
