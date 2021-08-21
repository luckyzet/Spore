package com.luckyzz.sporegame.character;

import com.luckyzz.sporegame.user.SporeUser;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

public interface CharacterRespawnProcessor {

    @NotNull PlayerRespawnEvent doRespawn(@NotNull final SporeUser user, @NotNull final PlayerRespawnEvent event);

}
