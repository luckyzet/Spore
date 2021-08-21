package com.luckyzz.sporegame.chat;

import com.akamecoder.cristalix.message.Message;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.util.Log;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatMessage {

    private final SporeUser user;
    private final Message message;
    private final List<Player> recipients;

    ChatMessage(@NotNull final SporeUser user, @NotNull final Message message, @NotNull final List<Player> recipients) {
        this.user = user;
        this.message = message;
        this.recipients = recipients;
    }

    public @NotNull SporeUser getUser() {
        return user;
    }

    public @NotNull Message getMessage() {
        return message;
    }

    public @NotNull List<Player> getRecipients() {
        return recipients;
    }

    /**
     * Send messages for all recipients
     */
    public void apply() {
        if(getRecipients().isEmpty()) {
            return;
        }
        getRecipients().forEach(message::send);
        Log.info(message.toRawText());
    }

}
