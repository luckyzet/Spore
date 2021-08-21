package com.luckyzz.sporegame.sidebar;

import com.akamecoder.cristalix.sidebar.SidebarService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class SporeSidebarManagement {

    public SporeSidebarManagement(@NotNull final Plugin plugin, @NotNull final SidebarService sidebarService) {
        final SporeSidebarStorage storage = new SporeSidebarStorage();
        storage.add(new SpawnSporeSidebar(sidebarService));
        storage.add(new ArenaSporeSidebar(sidebarService));

        new SidebarChangeListener(plugin, storage, sidebarService);
    }

}
