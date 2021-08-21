package com.luckyzz.sporegame.food;

import com.akamecoder.cristalix.api.Cancelable;
import com.luckyzz.sporegame.config.SettingConfig;
import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class SporeFoodManagement implements Cancelable {

    private final FoodMap foodMap;
    private final SporeFoodProcessor processor;
    private final SporeFoodResolver resolver;

    public SporeFoodManagement(@NotNull final Plugin plugin, @NotNull final SettingConfig config, @NotNull final SporeUserService userService) {
        foodMap = new FoodMap();
        resolver = new SporeFoodResolverImpl(foodMap);
        processor = new LimitedSporeFoodProcessor(config, foodMap, resolver);
        new FoodListener(plugin, userService, resolver, foodMap);
    }

    public @NotNull SporeFoodProcessor getProcessor() {
        return processor;
    }

    public @NotNull SporeFoodResolver getResolver() {
        return resolver;
    }

    @Override
    public void cancel() {
        foodMap.forEach(food -> {
            if(!food.isDead()) {
                food.getEntity().remove(); // Remove all chickens anyway on start
            }
        });
    }

}
