package com.luckyzz.sporegame.menu;

import com.akamecoder.cristalix.command.ChatCommand;
import com.akamecoder.cristalix.command.CommandOptions;
import com.akamecoder.cristalix.command.CommandSession;
import com.akamecoder.cristalix.command.PreconditionList;
import com.akamecoder.cristalix.command.precondition.ChatCommandPrecondition;
import com.akamecoder.cristalix.command.precondition.ChatCommandPreconditions;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserService;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import org.jetbrains.annotations.NotNull;

public class MenuCommand extends ChatCommand {

    private final SporeUserService userService;

    public MenuCommand(@NotNull final SporeUserService userService) {
        this.userService = userService;
        this.apply(CommandOptions.commandOptions().label("menu"));
    }

    @Override
    protected void preconditions(@NotNull final PreconditionList list) {
        list.precondition(ChatCommandPreconditions.executorPlayer().action(session -> {
            session.getExecutor().send(SporeMessages.FAILED_ONLY_PLAYER);
        }));

        list.precondition(ChatCommandPrecondition.precondition(session -> {
            final SporeUser user = userService.getUser(session.getExecutor().getPlayerHandle());
            return user.getCharacterSession().isPresent();
        }, session -> {
            session.getExecutor().send(SporeMessages.FAILED_SELECT_WORLD_MENU);
        }));
    }

    @Override
    protected void execution(@NotNull final CommandSession session) {
        final SporeUser user = userService.getUser(session.getExecutor().getPlayerHandle());

        new UserMenu(user);
    }

}
