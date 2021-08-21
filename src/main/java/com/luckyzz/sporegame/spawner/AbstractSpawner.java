package com.luckyzz.sporegame.spawner;

import com.luckyzz.sporegame.food.SporeFoodProcessor;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractSpawner implements Spawner {

    protected final String name;
    protected final SporeFoodProcessor foodProcessor;
    protected final FoodCategory category;
    protected final Location location;

    protected AbstractSpawner(@NotNull final SporeFoodProcessor foodProcessor, @NotNull final String name, @NotNull final FoodCategory category, @NotNull final Location location) {
        this.foodProcessor = foodProcessor;
        this.name = name;
        this.category = category;
        this.location = location;
    }

    private double generate(final int distance) {
        return ThreadLocalRandom.current().nextDouble(-distance, distance);
    }

    protected @NotNull Location generateLocation(final int distance) {
        if(distance == 0) {
            return location.clone();
        }

        final double x = generate(distance);
        final double z = generate(distance);

        final Location generated = location.add(x, 0, z);
        generated.setY(location.getWorld().getHighestBlockYAt(location));
        return generated;
    }

    protected void spawn(@NotNull final Location location) {
        foodProcessor.spawn(category, location);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull FoodCategory getCategory() {
        return category;
    }

    @Override
    public @NotNull Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractSpawner that = (AbstractSpawner) o;
        return new EqualsBuilder()
                .append(foodProcessor, that.foodProcessor)
                .append(name, that.name)
                .append(category, that.category)
                .append(location, that.location)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(foodProcessor)
                .append(name)
                .append(category)
                .append(location)
                .toHashCode();
    }
}
