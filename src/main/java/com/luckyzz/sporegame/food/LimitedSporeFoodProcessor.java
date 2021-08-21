package com.luckyzz.sporegame.food;

import com.luckyzz.sporegame.food.limit.FoodLimits;
import com.luckyzz.sporegame.config.SettingConfig;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

class LimitedSporeFoodProcessor implements SporeFoodProcessor {

    private final SettingConfig config;
    private final FoodMap foodMap;
    private final SporeFoodResolver resolver;

    LimitedSporeFoodProcessor(@NotNull final SettingConfig config, @NotNull final FoodMap foodMap, @NotNull final SporeFoodResolver resolver) {
        this.config = config;
        this.foodMap = foodMap;
        this.resolver = resolver;
    }

    @Override
    public @NotNull SporeFood spawn(@NotNull final FoodCategory category, @NotNull final Location location) {
        final FoodSpecifications specifications = config.getFoodSpecifications(category);
        if(resolver.resolve(category).size() >= FoodLimits.getLimit(category).getLimit()) {
            return new EmptySporeFood(specifications);
        }
        final SporeFood food = new SporeFoodImpl(specifications, () -> {
            final World world = location.getWorld();
            final LivingEntity entity = (LivingEntity) world.spawnEntity(location, EntityType.CHICKEN);
            entity.setCanPickupItems(false);
            specifications.applyTo(entity);
            return entity;
        });
        foodMap.put(food);
        return food;
    }

}
