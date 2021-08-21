package com.luckyzz.sporegame.chat;

import com.akamecoder.cristalix.function.Predicates;
import com.akamecoder.cristalix.message.Message;
import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EmptySporeChat extends AbstractSporeChat {

    EmptySporeChat() {
        super(Predicates.alwaysTrue());
    }

    @Override
    public @NotNull ChatMessage format(@NotNull final SporeUser user, @NotNull final String message) {
        return new ChatMessage(user, Message.empty(), new ArrayList<>());
    }

}
