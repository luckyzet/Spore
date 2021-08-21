package com.luckyzz.sporegame.character.item;

import com.luckyzz.sporegame.item.SporeItem;
import org.jetbrains.annotations.NotNull;

public interface CharacterItem {

    /**
     * @return index in the storage
     */
    int getIndex();

    @NotNull SporeItem getItem();

}
