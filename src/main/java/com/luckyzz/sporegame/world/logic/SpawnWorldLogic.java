package com.luckyzz.sporegame.world.logic;

import com.luckyzz.sporegame.spawning.SpawningLocationSelector;
import com.luckyzz.sporegame.world.SpawningPart;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.jetbrains.annotations.NotNull;

public class SpawnWorldLogic implements SporeWorldLogic {

    private final SpawningLocationSelector selector;

    public SpawnWorldLogic(@NotNull final SpawningLocationSelector selector) {
        this.selector = selector;
    }

    @Override
    public void move(@NotNull final PlayerMoveEvent event) {
    }

    @Override
    public void drop(@NotNull final PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void pickup(@NotNull final PlayerAttemptPickupItemEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void pickup(@NotNull final PlayerPickupArrowEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void pickup(@NotNull final PlayerPickupExperienceEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void damage(@NotNull final EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void damage(@NotNull final EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void jump(@NotNull final PlayerJumpEvent event) {
    }

    @Override
    public void food(@NotNull final FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }

    @Override
    public void death(@NotNull final PlayerDeathEvent event) {
    }

    @Override
    public void respawn(@NotNull final PlayerRespawnEvent event) {
        event.setRespawnLocation(selector.selectLocation(SpawningPart.SPAWN).getLocation());
    }

    @Override
    public void spawn(@NotNull final CreatureSpawnEvent event) {
        if(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) {
            return;
        }
        event.setCancelled(true);
    }

}
