package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.character.CharacterDeathProcessor;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class CharacterDeathProcessorImpl implements CharacterDeathProcessor {

    @Override
    public @NotNull PlayerDeathEvent doDeath(@Nullable final SporeUser killer, @NotNull final SporeUser killed, @NotNull final PlayerDeathEvent event) {
        event.setDroppedExp(0);
        event.setDeathMessage("");
        event.setKeepInventory(false);
        event.setKeepLevel(false);

        if(killer == null) {
            
            return event;
        }

        return event;
    }

}
