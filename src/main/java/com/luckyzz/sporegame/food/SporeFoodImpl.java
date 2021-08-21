package com.luckyzz.sporegame.food;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

class SporeFoodImpl implements SporeFood {

    private final FoodSpecifications specifications;
    private final LivingEntity entity;

    SporeFoodImpl(@NotNull final FoodSpecifications specifications, @NotNull final Supplier<LivingEntity> entity) {
        this.specifications = specifications;
        this.entity = entity.get();
    }

    @Override
    public @NotNull FoodSpecifications getSpecifications() {
        return specifications;
    }

    @Override
    public @NotNull LivingEntity getEntity() {
        return entity;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SporeFoodImpl sporeFood = (SporeFoodImpl) o;
        return new EqualsBuilder()
                .append(specifications, sporeFood.specifications)
                .append(entity, sporeFood.entity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(specifications)
                .append(entity)
                .toHashCode();
    }
}
