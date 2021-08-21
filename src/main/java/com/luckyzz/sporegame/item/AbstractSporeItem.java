package com.luckyzz.sporegame.item;

import com.luckyzz.sporegame.item.logic.SporeItemLogic;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSporeItem implements SporeItem {

    protected final SporeItemSide side;
    protected final SporeItemType type;
    protected final int index;
    protected final ItemStack itemStack;
    protected final SporeItemSpecifications specifications;
    protected final SporeItemLogic logic;

    protected AbstractSporeItem(@NotNull final SporeItemSide side, @NotNull final SporeItemType type, final int index,
                                @NotNull final ItemStack itemStack, @NotNull final SporeItemSpecifications specifications,
                                @NotNull final SporeItemLogic logic) {
        this.side = side;
        this.type = type;
        this.index = index;
        this.itemStack = itemStack;
        this.specifications = specifications;
        this.logic = logic;
    }

    @Override
    public @NotNull SporeItemSide getSide() {
        return side;
    }

    @Override
    public @NotNull SporeItemType getType() {
        return type;
    }

    @Override
    public int getInventoryIndex() {
        return index;
    }

    @Override
    public @NotNull ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public @NotNull SporeItemSpecifications getSpecifications() {
        return specifications;
    }

    @Override
    public @NotNull SporeItemLogic getLogic() {
        return logic;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractSporeItem that = (AbstractSporeItem) o;
        return new EqualsBuilder()
                .append(index, that.index)
                .append(side, that.side)
                .append(type, that.type)
                .append(itemStack, that.itemStack)
                .append(specifications, that.specifications)
                .append(logic, that.logic)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(side)
                .append(type)
                .append(index)
                .append(itemStack)
                .append(specifications)
                .append(logic)
                .toHashCode();
    }
}
