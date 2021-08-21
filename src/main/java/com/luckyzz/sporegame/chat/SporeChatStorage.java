package com.luckyzz.sporegame.chat;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class SporeChatStorage {

    private final Set<SporeChat> set = new HashSet<>();

    SporeChatStorage() {
    }

    public @NotNull SporeChat getChat(@NotNull final SporeUser user) {
        return set.stream()
                .filter(chat -> chat.getPredicate().test(user))
                .findFirst().orElse(new EmptySporeChat());
    }

    void add(@NotNull final SporeChat chat) {
        set.add(chat);
    }

}
