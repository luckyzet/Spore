package com.luckyzz.sporegame.spawning;

import com.akamecoder.cristalix.api.Cancelable;
import org.jetbrains.annotations.NotNull;

interface SpawningLocationRepository extends Cancelable {

    void saveSpawningLocation(@NotNull final SpawningLocation location);

}
