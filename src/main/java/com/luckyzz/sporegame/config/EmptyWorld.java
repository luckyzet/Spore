package com.luckyzz.sporegame.config;

import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.world.SporeWorldType;
import com.luckyzz.sporegame.world.logic.SporeWorldLogic;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class EmptyWorld implements SporeWorld {

    EmptyWorld() {
    }

    @Override
    public @NotNull SporeWorldType getType() {
        return null;
    }

    @Override
    public @NotNull World getWorld() {
        return null;
    }

    @Override
    public @Nullable SporeWorldLogic getLogic() {
        return null;
    }

}
