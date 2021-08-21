package com.luckyzz.sporegame.sidebar;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

class SporeSidebarStorage {

    private final Set<SporeSidebar> set = new HashSet<>();

    SporeSidebarStorage() {
    }

    public @NotNull SporeSidebar getSidebar(@NotNull final SporeUser user) {
        return set.stream()
                .filter(sidebar -> sidebar.getPredicate().test(user))
                .findFirst().orElse(new EmptySporeSidebar(null));
    }

    void add(@NotNull final SporeSidebar sidebar) {
        set.add(sidebar);
    }

}
