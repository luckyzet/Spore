package com.luckyzz.sporegame.world.logic;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.jetbrains.annotations.NotNull;

public interface SporeWorldLogic {

    void move(@NotNull final PlayerMoveEvent event);

    void drop(@NotNull final PlayerDropItemEvent event);

    void pickup(@NotNull final PlayerAttemptPickupItemEvent event);

    void pickup(@NotNull final PlayerPickupArrowEvent event);

    void pickup(@NotNull final PlayerPickupExperienceEvent event);

    void damage(@NotNull final EntityDamageByEntityEvent event);

    void damage(@NotNull final EntityDamageEvent event);

    void jump(@NotNull final PlayerJumpEvent event);

    void food(@NotNull final FoodLevelChangeEvent event);

    void death(@NotNull final PlayerDeathEvent event);

    void respawn(@NotNull final PlayerRespawnEvent event);

    void spawn(@NotNull final CreatureSpawnEvent event);

}
