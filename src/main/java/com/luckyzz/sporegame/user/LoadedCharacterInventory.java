package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.character.inventory.CharacterInventory;
import com.luckyzz.sporegame.character.inventory.CharacterSlot;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class LoadedCharacterInventory implements CharacterInventory {

    private final SporeUser user;
    private final Map<Integer, CharacterSlot> slotMap;

    LoadedCharacterInventory(@NotNull final SporeUser user, @NotNull final Map<Integer, CharacterSlot> slotMap) {
        this.user = user;
        this.slotMap = new HashMap<>(slotMap);
    }

    @Override
    public @NotNull SporeUser getUser() {
        return user;
    }

    @Override
    public @NotNull CharacterSlot getSlot(final int index) {
        return slotMap.get(index);
    }

    @Override
    public void applyInventory() {

    }
}
