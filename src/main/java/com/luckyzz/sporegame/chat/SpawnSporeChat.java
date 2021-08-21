package com.luckyzz.sporegame.chat;

import com.akamecoder.cristalix.function.Predicates;
import com.akamecoder.cristalix.message.Message;
import com.akamecoder.cristalix.util.player.PlayerFilters;
import com.luckyzz.sporegame.user.SporeUser;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

class SpawnSporeChat extends AbstractSporeChat {

    SpawnSporeChat() {
        super(user -> !user.getCharacterSession().isPresent());
    }

    @Override
    public @NotNull ChatMessage format(@NotNull final SporeUser user, @NotNull String message) {
        if(message.startsWith("!")) {
            message = message.replaceFirst("!", "");
            if(message.length() == 0) {
                return new ChatMessage(user, Message.empty(), new ArrayList<>());
            }

            final Message buildingMessage = Message.create(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "G" + ChatColor.DARK_GRAY + "] " +
                    ChatColor.GRAY + user.getName() + " » " + ChatColor.GREEN + message);

            return new ChatMessage(user, buildingMessage, PlayerFilters.online().stream().collect(Collectors.toList()));
        }

        final Message buildingMessage = Message.create(ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "L" + ChatColor.DARK_GRAY + "] " +
                ChatColor.GRAY + user.getName() + " » " + ChatColor.WHITE + message);

        return new ChatMessage(user, buildingMessage, PlayerFilters.predicate(Predicates.maximalDistance(user.getPlayer().getLocation(), 100))
                .stream()
                .collect(Collectors.toList()));
    }

}
