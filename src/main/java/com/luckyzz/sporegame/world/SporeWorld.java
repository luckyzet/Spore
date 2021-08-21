package com.luckyzz.sporegame.world;

import com.akamecoder.cristalix.api.Typable;
import com.luckyzz.sporegame.world.logic.SporeWorldLogic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface SporeWorld extends Typable<SporeWorldType> {

    default boolean isThatWorld(@NotNull final World world) {
        return getWorld().equals(world);
    }

    default boolean isInWorld(@NotNull final Player player) {
        return isThatWorld(player.getLocation().getWorld());
    }

    @NotNull World getWorld();

    default @NotNull String getName() {
        return getWorld().getName();
    }

    default @NotNull UUID getUniqueId() {
        return getWorld().getUID();
    }

    @Nullable SporeWorldLogic getLogic();

}
