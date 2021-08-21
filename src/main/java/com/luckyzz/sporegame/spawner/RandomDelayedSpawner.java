package com.luckyzz.sporegame.spawner;

import com.luckyzz.sporegame.food.SporeFoodProcessor;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class RandomDelayedSpawner extends AbstractSpawner {

    private final int minDelay;
    private final int maxDelay;
    private final int distance;

    private int timeLeft;

    RandomDelayedSpawner(@NotNull final SporeFoodProcessor foodProcessor, @NotNull final String name, @NotNull final FoodCategory category, @NotNull final Location location,
                         final int minDelay, final int maxDelay, final int distance) {
        super(foodProcessor, name, category, location);
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
        this.distance = distance;
        this.timeLeft = ThreadLocalRandom.current().nextInt(minDelay, maxDelay);
    }

    public int getMinDelay() {
        return minDelay;
    }

    public int getMaxDelay() {
        return maxDelay;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public @NotNull SpawnerType getType() {
        return SpawnerType.RANDOM_DELAYED;
    }

    @Override
    public boolean doTick() {
        if(--timeLeft > 0) {
            return false;
        }
        spawn(generateLocation(distance));
        timeLeft = ThreadLocalRandom.current().nextInt(minDelay, maxDelay);
        return true;
    }

}
