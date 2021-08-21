package com.luckyzz.sporegame.sidebar;

import com.akamecoder.cristalix.sidebar.SidebarIndividual;
import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Predicate;

public interface SporeSidebar {

    @NotNull Predicate<SporeUser> getPredicate();

    @NotNull Function<SporeUser, SidebarIndividual> getCreationFunction();

}
