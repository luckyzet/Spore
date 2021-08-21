package com.luckyzz.sporegame.chat;

import com.akamecoder.cristalix.component.builder.TextComponentBuilder;
import com.akamecoder.cristalix.function.Predicates;
import com.akamecoder.cristalix.message.Message;
import com.akamecoder.cristalix.util.player.PlayerFilters;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.chat.exception.ChatException;
import com.luckyzz.sporegame.user.SporeUser;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Collectors;

class ArenaSporeChat extends AbstractSporeChat {

    ArenaSporeChat() {
        super(user -> user.getCharacterSession().isPresent());
    }

    @Override
    public @NotNull ChatMessage format(@NotNull final SporeUser user, @NotNull String message) {
        if(message.startsWith("!")) {
            message = message.replaceFirst("!", "");
            if(message.length() == 0) {
                return new ChatMessage(user, Message.empty(), new ArrayList<>());
            }

            final SporeCharacterSession session = user.getCharacterSession().orElseThrow(() -> {
                throw new ChatException("User has not active character session!");
            });

            final TextComponentBuilder builder = TextComponentBuilder.newBuilder()
                    .text(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "G" + ChatColor.DARK_GRAY + "] ")
                    .extra(TextComponentBuilder.newBuilder()
                            .text(ChatColor.GRAY + session.getName())
                            .onHover(HoverEvent.Action.SHOW_TEXT, user.getName())
                    ).extra(TextComponentBuilder.newBuilder()
                            .text(" " + session.getDetailedLevel().getRomanIndex()  + " » " + ChatColor.GREEN + message)
                    );

            return new ChatMessage(user, Message.create(builder.create()), PlayerFilters.online().stream().collect(Collectors.toList()));
        }

        final SporeCharacterSession session = user.getCharacterSession().orElseThrow(() -> {
            throw new ChatException("User has not active character session!");
        });

        final TextComponentBuilder builder = TextComponentBuilder.newBuilder()
                .text(ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "L" + ChatColor.DARK_GRAY + "] ")
                .extra(TextComponentBuilder.newBuilder()
                        .text(ChatColor.GRAY + session.getName())
                        .onHover(HoverEvent.Action.SHOW_TEXT, user.getName())
                ).extra(TextComponentBuilder.newBuilder()
                        .text(" " + session.getDetailedLevel().getRomanIndex()  + " » " + ChatColor.WHITE + message)
                );

        return new ChatMessage(user, Message.create(builder.create()), PlayerFilters.predicate(Predicates.maximalDistance(user.getPlayer().getLocation(), 100))
                .stream()
                .collect(Collectors.toList()));
    }

}
