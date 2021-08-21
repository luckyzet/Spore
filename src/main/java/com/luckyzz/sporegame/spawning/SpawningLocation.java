package com.luckyzz.sporegame.spawning;

import com.luckyzz.sporegame.world.SpawningPart;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface SpawningLocation {

    @NotNull SpawningPart getPart();

    @NotNull Location getLocation();

    default void teleport(@NotNull final Entity entity) {
        entity.teleport(getLocation());
    }

}
