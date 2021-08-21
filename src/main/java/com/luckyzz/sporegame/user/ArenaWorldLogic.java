package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.world.logic.AbstractSporeWorldLogic;
import com.luckyzz.sporegame.character.CharacterDeathProcessor;
import com.luckyzz.sporegame.character.CharacterRespawnProcessor;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.jetbrains.annotations.NotNull;

public class ArenaWorldLogic extends AbstractSporeWorldLogic {

    private final CharacterDeathProcessor deathProcessor;
    private final CharacterRespawnProcessor respawnProcessor;

    public ArenaWorldLogic(@NotNull final SporeUserService userService, @NotNull final CharacterDeathProcessor deathProcessor,
                           @NotNull final CharacterRespawnProcessor respawnProcessor) {
        super(userService);
        this.deathProcessor = deathProcessor;
        this.respawnProcessor = respawnProcessor;
    }

    @Override
    public void move(@NotNull final PlayerMoveEvent event) {
        // TODO: Check with abilities
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
        // TODO: Check with abilities
    }

    @Override
    public void damage(@NotNull final EntityDamageEvent event) {
        // TODO: Check with abilities
    }

    @Override
    public void jump(@NotNull final PlayerJumpEvent event) {
        // TODO: Check with abilities
    }

    @Override
    public void food(@NotNull final FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }

    @Override
    public void death(@NotNull final PlayerDeathEvent event) {
        final Player killedPlayer = event.getEntity();
        final Player killerPlayer = killedPlayer.getKiller();

        final SporeUser killed = userService.getUser(killedPlayer);
        final SporeUser killer = killerPlayer != null ? userService.getUser(killerPlayer) : null;

        deathProcessor.doDeath(killer, killed, event);
    }

    @Override
    public void respawn(@NotNull final PlayerRespawnEvent event) {
        final SporeUser user = userService.getUser(event.getPlayer());
        respawnProcessor.doRespawn(user, event);
    }

    @Override
    public void spawn(@NotNull final CreatureSpawnEvent event) {
        if(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) {
            return;
        }
        event.setCancelled(true);
    }
}
