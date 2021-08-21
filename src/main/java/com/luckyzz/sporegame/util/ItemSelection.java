package com.luckyzz.sporegame.util;

import com.akamecoder.cristalix.util.itemstack.builder.ItemStackBuilder;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemSelection {

    private ItemSelection() {
        throw new UnsupportedOperationException();
    }

    public static @NotNull ItemStack selection(@NotNull ItemStack itemStack) {
        itemStack = itemStack.clone();
        itemStack.addEnchantment(Enchantment.KNOCKBACK, 1);
        itemStack.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return itemStack;
    }

    public static @NotNull ItemStackBuilder<?> selection(@NotNull final ItemStackBuilder<?> itemStack) {
        return itemStack.enchantment(Enchantment.KNOCKBACK, 1)
                .flags(ItemFlag.HIDE_ENCHANTS);
    }

}
