package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.character.item.CharacterItem;
import com.luckyzz.sporegame.item.SporeItem;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class CharacterItemImpl implements CharacterItem {

    private final int index;
    private final SporeItem item;

    CharacterItemImpl(final int index, @NotNull final SporeItem item) {
        this.index = index;
        this.item = item;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public @NotNull SporeItem getItem() {
        return item;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CharacterItemImpl that = (CharacterItemImpl) o;
        return new EqualsBuilder()
                .append(index, that.index)
                .append(item, that.item)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(index)
                .append(item)
                .toHashCode();
    }
}
