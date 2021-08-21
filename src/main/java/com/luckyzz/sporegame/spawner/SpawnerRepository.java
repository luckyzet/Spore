package com.luckyzz.sporegame.spawner;

import com.akamecoder.cristalix.api.Cancelable;
import org.jetbrains.annotations.NotNull;

public interface SpawnerRepository extends Cancelable {

    void saveSpawner(@NotNull final Spawner spawner);

}
