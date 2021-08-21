package com.luckyzz.sporegame.food;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptySporeFood implements SporeFood {

    private final FoodSpecifications specifications;

    public EmptySporeFood(@NotNull final FoodSpecifications specifications) {
        this.specifications = specifications;
    }

    @Override
    public @NotNull FoodSpecifications getSpecifications() {
        return specifications;
    }

    @Override
    public @NotNull LivingEntity getEntity() {
        return null;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final EmptySporeFood that = (EmptySporeFood) o;
        return new EqualsBuilder().append(specifications, that.specifications).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(specifications).toHashCode();
    }
}
