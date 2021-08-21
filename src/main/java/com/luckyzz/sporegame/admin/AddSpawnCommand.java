package com.luckyzz.sporegame.admin;

import com.akamecoder.cristalix.command.*;
import com.akamecoder.cristalix.command.precondition.ChatCommandPrecondition;
import com.luckyzz.sporegame.spawning.SpawningLocationFactory;
import com.luckyzz.sporegame.world.SpawningPart;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

class AddSpawnCommand extends ChatCommand {

    private final SpawningLocationFactory factory;

    AddSpawnCommand(@NotNull final SpawningLocationFactory factory) {
        this.factory = factory;
        this.apply(CommandOptions.subCommandOptions().label("add"));
    }

    @Override
    protected void executionPreconditions(@NotNull final PreconditionList list) {
        list.precondition(ChatCommandPrecondition.precondition(session -> session.getArguments().length() == 1, session -> {
            session.getExecutor().send(ChatColor.GRAY + "Используй: " + ChatColor.AQUA + "/sporeadmin spawn add [Часть мира]\n" +
                    ChatColor.GRAY + "  | Часть мира (1,2,3,4), также можно указать 0 - это дефолт точка спавна на самом спавне");
        }));
    }

    @Override
    protected void execution(@NotNull final CommandSession session) {
        final Player player = session.getExecutor().getPlayerHandle();
        final String spawningPartString = session.getArguments().get(1);

        SpawningPart part;
        try {
            part = SpawningPart.fromIndex(Integer.parseInt(spawningPartString));
        } catch (final Exception exception) {
            player.sendMessage(SporeMessages.ERROR_PREFIX + "Указана неправильная часть мира! \n" +
                    "| Часть мира (1,2,3,4), также можно указать 0 - это дефолт точка спавна на самом спавне");
            return;
        }

        factory.createSpawningLocation(part, player.getLocation());
        player.sendMessage(SporeMessages.SUCCESS_PREFIX + "Вы успешно создали новую точку спавна!");
    }

}
