package com.luckyzz.sporegame.select;

import com.akamecoder.cristalix.command.*;
import com.akamecoder.cristalix.command.precondition.ChatCommandPrecondition;
import com.akamecoder.cristalix.command.precondition.ChatCommandPreconditions;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserService;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SelectCharacterCommand extends ChatCommand {

    private final SporeUserService userService;
    private final SelectCharacterProcessor processor;

    SelectCharacterCommand(@NotNull final SporeUserService userService, @NotNull final SelectCharacterProcessor processor) {
        this.userService = userService;
        this.processor = processor;
        this.apply(CommandOptions.commandOptions().label("selectcharacter").aliases("select", "character"));
    }

    @Override
    protected void preconditions(@NotNull final PreconditionList list) {
        list.precondition(ChatCommandPreconditions.executorPlayer().action(session -> {
            session.getExecutor().send(SporeMessages.FAILED_ONLY_PLAYER);
        }));

        list.precondition(ChatCommandPrecondition.precondition(session -> {
            final SporeUser user = userService.getUser(session.getExecutor().getPlayerHandle());
            return !user.getCharacterSession().isPresent();
        }, session -> {
            session.getExecutor().send(SporeMessages.FAILED_SELECT_WORLD_CHARACTER);
        }));
    }

    @Override
    protected void execution(@NotNull final CommandSession session) {
        final Executor executor = session.getExecutor();
        final Player player = executor.getPlayerHandle();

        processor.selectFor(userService.getUser(player));
    }

}
