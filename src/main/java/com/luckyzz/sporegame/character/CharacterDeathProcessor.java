package com.luckyzz.sporegame.character;

import com.luckyzz.sporegame.user.SporeUser;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CharacterDeathProcessor {

    @NotNull PlayerDeathEvent doDeath(@Nullable final SporeUser killer, @NotNull final SporeUser killed, @NotNull final PlayerDeathEvent event);

}
