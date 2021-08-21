package com.luckyzz.sporegame.admin;

import com.akamecoder.cristalix.command.ChatCommand;
import com.akamecoder.cristalix.command.CommandOptions;
import com.akamecoder.cristalix.command.CommandSession;
import com.akamecoder.cristalix.command.SubCommandList;
import com.luckyzz.sporegame.spawner.SpawnerManagement;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

class SpawnerCommand extends ChatCommand {

    private final SpawnerManagement spawnerManagement;

    SpawnerCommand(@NotNull final SpawnerManagement spawnerManagement) {
        this.spawnerManagement = spawnerManagement;
        this.apply(CommandOptions.subCommandOptions().label("spawner"));
    }

    @Override
    protected void subCommands(@NotNull final SubCommandList list) {
        list.subCommand(new AddSpawnerCommand(spawnerManagement.getFactory()));
    }

    private void help(@NotNull final CommandSession session) {
        session.getExecutor().send(
                ChatColor.GRAY + "[" + ChatColor.AQUA + "Spore" + ChatColor.GRAY + "] | Помощь по командам /sporeadmin spawner:\n" +
                        ChatColor.AQUA + "/sporeadmin spawner add [Тип спавнера] [Категория] [Название] [Прочие характеристики]" + ChatColor.GRAY + " - добавить спавнер\n" +
                        ChatColor.AQUA + "  | Тип спавнера - RANDOM_DELAYED(спавнер, который спавнит моба в рандомно сгенирированный период)\n" +
                        ChatColor.AQUA + "  | Категория - тип еды и ее уроверь. Указывается следующим образом: MEAT 1 или GRASS 1\n" +
                        ChatColor.AQUA + "  | Название - любое имя, ни на что не влияет\n" +
                        ChatColor.AQUA + "  | Прочие характерисики - зависят от типа спавнера\n" +
                        ChatColor.AQUA + "  |  RANDOM_DELAYED: минимальная задержка, максимальная задержка, разброс при спавне(максимальное смещение локации)\n" +
                        ChatColor.AQUA + "  |  /sporeadmin spawner add RANDOM_DELAYED MEAT 1 Meat1 15 60 5"
        );
    }

    @Override
    protected void execution(@NotNull final CommandSession session) {
        help(session);
    }

}
