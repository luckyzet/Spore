package com.luckyzz.sporegame.spawning;

import com.luckyzz.sporegame.world.SpawningPart;
import org.jetbrains.annotations.NotNull;

public interface SpawningLocationSelector {

    @NotNull SpawningLocation selectLocation(@NotNull final SpawningPart part);

}
