package com.luckyzz.sporegame.item;

import com.luckyzz.sporegame.item.logic.SporeItemLogic;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface SporeItem {

    @NotNull SporeItemSide getSide();

    @NotNull SporeItemType getType();

    int getInventoryIndex();

    @NotNull ItemStack getItemStack();

    @NotNull SporeItemSpecifications getSpecifications();

    @NotNull SporeItemLogic getLogic();

}
