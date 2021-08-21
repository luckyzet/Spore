package com.luckyzz.sporegame.spawning;

import com.luckyzz.sporegame.world.SpawningPart;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Stream;

class SpawningLocationStorage {

    private final Multimap<SpawningPart, SpawningLocation> map;

    SpawningLocationStorage(@NotNull final Multimap<SpawningPart, SpawningLocation> map) {
        this.map = map;
    }

    SpawningLocationStorage() {
        this(HashMultimap.create());
    }

    public @NotNull Stream<SpawningLocation> stream() {
        return map.values().stream();
    }

    public @NotNull Collection<SpawningLocation> getPart(@NotNull final SpawningPart part) {
        return map.get(part);
    }

    public int size() {
        return map.size();
    }

    boolean put(@NotNull final SpawningLocation location) {
        return map.put(location.getPart(), location);
    }

}
