package com.luckyzz.sporegame.config;

import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.world.SporeWorldType;
import com.luckyzz.sporegame.world.logic.SporeWorldLogic;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class WorldImpl implements SporeWorld {

    private final SporeWorldType type;
    private final World world;
    private SporeWorldLogic logic;
    
    WorldImpl(@NotNull final SporeWorldType type, @NotNull final String name, @NotNull final SporeWorldLogic logic) {
        this.type = type;
        this.logic = logic;

        World w = Bukkit.getWorld(name);
        if(w == null) {
            w = new WorldCreator(name).createWorld();
        }

        this.world = w;
    }

    @Override
    public @NotNull SporeWorldType getType() {
        return type;
    }

    @Override
    public @NotNull World getWorld() {
        return world;
    }

    @Override
    public @Nullable SporeWorldLogic getLogic() {
        return logic;
    }

    void setLogic(@NotNull final SporeWorldLogic logic) {
        this.logic = logic;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WorldImpl world1 = (WorldImpl) o;
        return new EqualsBuilder()
                .append(type, world1.type)
                .append(world, world1.world)
                .append(logic, world1.logic)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type)
                .append(world)
                .append(logic)
                .toHashCode();
    }
}
