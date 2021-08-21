package com.luckyzz.sporegame.spawning;

import com.luckyzz.sporegame.world.SpawningPart;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SpawningLocationImpl implements SpawningLocation {

    private final SpawningPart part;
    private final Location location;

    SpawningLocationImpl(@NotNull final SpawningPart part, @NotNull final Location location) {
        this.part = part;
        this.location = location;
    }

    @Override
    public @NotNull SpawningPart getPart() {
        return part;
    }

    @Override
    public @NotNull Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SpawningLocationImpl that = (SpawningLocationImpl) o;
        return new EqualsBuilder().append(part, that.part).append(location, that.location).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(part).append(location).toHashCode();
    }
}
