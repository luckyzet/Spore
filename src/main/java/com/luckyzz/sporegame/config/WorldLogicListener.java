package com.luckyzz.sporegame.config;

import com.akamecoder.cristalix.event.handle.EventHandle;
import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.world.logic.SporeWorldLogic;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class WorldLogicListener extends EventHandle {

    private final SporeWorld world;
    private final SporeWorldLogic logic;

    WorldLogicListener(@NotNull final Plugin plugin, @NotNull final SporeWorld world) {
        super(plugin);
        this.world = world;
        this.logic = world.getLogic();
    }

    private boolean checkWorld(@NotNull final PlayerEvent event) {
        if(logic == null) {
            return true;
        }
        return !this.world.isThatWorld(event.getPlayer().getWorld());
    }

    private boolean checkWorld(@NotNull final EntityEvent event) {
        if(logic == null) {
            return true;
        }
        return !this.world.isThatWorld(event.getEntity().getWorld());
    }

    @EventHandler
    public void onMove(@NotNull final PlayerMoveEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.move(event);
    }

    @EventHandler
    public void drop(@NotNull final PlayerDropItemEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.drop(event);
    }

    @EventHandler
    public void pickup(@NotNull final PlayerAttemptPickupItemEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.pickup(event);
    }

    @EventHandler
    public void pickup(@NotNull final PlayerPickupArrowEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.pickup(event);
    }

    @EventHandler
    public void pickup(@NotNull final PlayerPickupExperienceEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.pickup(event);
    }

    @EventHandler
    public void damage(@NotNull final EntityDamageByEntityEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.damage(event);
    }

    @EventHandler
    public void damage(@NotNull final EntityDamageEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.damage(event);
    }

    @EventHandler
    public void jump(@NotNull final PlayerJumpEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.jump(event);
    }

    @EventHandler
    public void food(@NotNull final FoodLevelChangeEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.food(event);
    }

    @EventHandler
    public void death(@NotNull final PlayerDeathEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.death(event);
    }

    @EventHandler
    public void respawn(@NotNull final PlayerRespawnEvent event) {
        if(checkWorld(event)) {
            return;
        }
        logic.respawn(event);
    }

}
