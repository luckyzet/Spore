package com.luckyzz.sporegame.character.inventory;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public interface CharacterInventory {

    @NotNull SporeUser getUser();

    @NotNull CharacterSlot getSlot(final int index);

    void applyInventory();

}
