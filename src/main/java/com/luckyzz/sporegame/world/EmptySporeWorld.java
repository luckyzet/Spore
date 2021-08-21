package com.luckyzz.sporegame.world;

import com.luckyzz.sporegame.world.logic.SporeWorldLogic;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptySporeWorld implements SporeWorld {

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
