package com.luckyzz.sporegame.food;

import com.akamecoder.cristalix.event.handle.EventHandle;
import com.luckyzz.sporegame.character.exception.CharacterException;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserService;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

final class FoodListener extends EventHandle {

    private final SporeUserService userService;
    private final SporeFoodResolver resolver;
    private final FoodMap foodMap;

    FoodListener(@NotNull final Plugin plugin, @NotNull final SporeUserService userService,
                 @NotNull final SporeFoodResolver resolver, @NotNull final FoodMap foodMap) {
        super(plugin);
        this.userService = userService;
        this.resolver = resolver;
        this.foodMap = foodMap;
    }

    @EventHandler
    public void onDeath(@NotNull final EntityDeathEvent event) {
        resolver.resolve(event.getEntity()).ifPresent(food -> {
            foodMap.remove(food);

            event.setDroppedExp(0);
            event.getDrops().clear();

            final LivingEntity entity = food.getEntity();
            final Player killer = entity.getKiller();
            if(killer == null) {
                return;
            }
            final FoodSpecifications specifications = food.getSpecifications();

            final SporeUser user = userService.getUser(killer);
            final SporeCharacterSession session = user.getCharacterSession().orElseThrow(() -> {
                return new CharacterException("Session has no exists!");
            });

            session.getDetailedLevel().changeCurrentProgress(specifications.getDna());
        });
    }

}
