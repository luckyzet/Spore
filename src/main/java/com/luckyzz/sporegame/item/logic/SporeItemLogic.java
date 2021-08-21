package com.luckyzz.sporegame.item.logic;

import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public interface SporeItemLogic {

    /**
     * Executing when player takes this item in inventory
     */
    void onTake(@NotNull final SporeCharacterSession user);

    /**
     * Executing when player removes this item in inventory
     */
    void onRemove(@NotNull final SporeCharacterSession user);

    void jump(@NotNull final SporeCharacterSession user);

    void damage(@NotNull final EntityDamageByEntityEvent event, @NotNull final SporeCharacterSession damager, @NotNull final SporeCharacterSession damaged);

    void interact(@NotNull final PlayerInteractEvent event, @NotNull final SporeCharacterSession user);

}
