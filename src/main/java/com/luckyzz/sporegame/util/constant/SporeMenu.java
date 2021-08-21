package com.luckyzz.sporegame.util.constant;

import com.akamecoder.cristalix.util.itemstack.builder.DefaultItemStackBuilder;
import com.akamecoder.cristalix.util.itemstack.builder.ItemStackBuilders;
import org.bukkit.Material;

public final class SporeMenu {

    public static final int[] SIDE_PANES = { 0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53 };

    private SporeMenu() {
        throw new UnsupportedOperationException();
    }

    public static final DefaultItemStackBuilder PANE = ItemStackBuilders.newBuilder()
            .type(Material.STAINED_GLASS_PANE)
            .durability((short) 3);

}
