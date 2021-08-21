package com.luckyzz.sporegame.sidebar;

import com.akamecoder.cristalix.function.Predicates;
import com.akamecoder.cristalix.sidebar.IndividualBukkitSidebar;
import com.akamecoder.cristalix.sidebar.SidebarGeneration;
import com.akamecoder.cristalix.sidebar.SidebarIndividual;
import com.akamecoder.cristalix.sidebar.SidebarService;
import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class EmptySporeSidebar extends AbstractSporeSidebar {

    EmptySporeSidebar(@NotNull final SidebarService sidebarService) {
        super(sidebarService, Predicates.alwaysTrue());
    }

    @Override
    public @NotNull Function<SporeUser, SidebarIndividual> getCreationFunction() {
        return user -> new IndividualBukkitSidebar() {
            @Override
            protected void generate(@NotNull final SidebarGeneration sidebarGeneration) {
            }
        };
    }

}
