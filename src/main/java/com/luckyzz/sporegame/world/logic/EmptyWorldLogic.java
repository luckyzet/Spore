package com.luckyzz.sporegame.world.logic;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.jetbrains.annotations.NotNull;

public class EmptyWorldLogic implements SporeWorldLogic {

    @Override
    public void move(@NotNull PlayerMoveEvent event) {

    }

    @Override
    public void drop(@NotNull PlayerDropItemEvent event) {

    }

    @Override
    public void pickup(@NotNull PlayerAttemptPickupItemEvent event) {

    }

    @Override
    public void pickup(@NotNull PlayerPickupArrowEvent event) {

    }

    @Override
    public void pickup(@NotNull PlayerPickupExperienceEvent event) {

    }

    @Override
    public void damage(@NotNull EntityDamageByEntityEvent event) {

    }

    @Override
    public void damage(@NotNull EntityDamageEvent event) {

    }

    @Override
    public void jump(@NotNull PlayerJumpEvent event) {

    }

    @Override
    public void food(@NotNull FoodLevelChangeEvent event) {

    }

    @Override
    public void death(@NotNull PlayerDeathEvent event) {

    }

    @Override
    public void respawn(@NotNull PlayerRespawnEvent event) {

    }

    @Override
    public void spawn(@NotNull CreatureSpawnEvent event) {

    }

}
