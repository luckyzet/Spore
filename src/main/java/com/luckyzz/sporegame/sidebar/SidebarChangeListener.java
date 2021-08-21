package com.luckyzz.sporegame.sidebar;

import com.akamecoder.cristalix.event.handle.EventHandle;
import com.akamecoder.cristalix.sidebar.SidebarIndividual;
import com.akamecoder.cristalix.sidebar.SidebarService;
import com.akamecoder.cristalix.util.key.PlayerUniqueKey;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.event.SporeUserSessionEvent;
import com.luckyzz.sporegame.user.join.JoinHandleStrategyExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

class SidebarChangeListener extends EventHandle {

    private final SporeSidebarStorage storage;
    private final SidebarService sidebarService;

    SidebarChangeListener(@NotNull final Plugin plugin, @NotNull final SporeSidebarStorage storage, @NotNull final SidebarService sidebarService) {
        super(plugin);
        this.storage = storage;
        this.sidebarService = sidebarService;

        new JoinHandleStrategyExecutor(plugin, EventPriority.MONITOR, this::showSidebar);
    }

    private void showSidebar(@NotNull final SporeUser user) {
        final SporeSidebar sidebarPattern = storage.getSidebar(user);

        final SidebarIndividual individual = sidebarService.getRegistration().get(PlayerUniqueKey.wrap(user.getPlayer()));
        if(individual != null) {
            sidebarService.getRegistration().remove(PlayerUniqueKey.wrap(user.getPlayer()));
            individual.hide();
        }

        sidebarPattern.getCreationFunction().apply(user);
    }

    @EventHandler
    public void onSession(@NotNull final SporeUserSessionEvent event) {
        showSidebar(event.getUser());
    }

}
