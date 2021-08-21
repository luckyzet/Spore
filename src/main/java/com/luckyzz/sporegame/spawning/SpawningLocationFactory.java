package com.luckyzz.sporegame.spawning;

import com.luckyzz.sporegame.world.SpawningPart;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface SpawningLocationFactory {

    @NotNull SpawningLocation createSpawningLocation(@NotNull final SpawningPart part, @NotNull final Location location);

}
