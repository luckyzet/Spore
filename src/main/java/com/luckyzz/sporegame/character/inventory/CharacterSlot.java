package com.luckyzz.sporegame.character.inventory;

import com.luckyzz.sporegame.item.SporeItem;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CharacterSlot {

    private final CharacterInventory inventory;
    private final int index;
    private SporeItem item;

    CharacterSlot(@NotNull final CharacterInventory inventory, final int index, @Nullable final SporeItem item) {
        this.inventory = inventory;
        this.index = index;
        this.item = item;
    }

    public int getIndex() {
        return index;
    }

    public @Nullable SporeItem getItem() {
        return item;
    }

    public @Nullable SporeItem setItem(@Nullable final SporeItem item) {
        final SporeItem old = this.item;
        this.item = item;
        inventory.applyInventory();
        return old;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CharacterSlot that = (CharacterSlot) o;
        return new EqualsBuilder()
                .append(index, that.index)
                .append(item, that.item)
                .append(inventory, that.inventory)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(index)
                .append(item)
                .append(inventory)
                .toHashCode();
    }
}
