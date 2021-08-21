package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.scheduler.SchedulerRunner;
import com.akamecoder.cristalix.scheduler.SchedulerTicks;
import com.luckyzz.sporegame.spawning.SpawningLocationSelector;
import com.luckyzz.sporegame.world.SpawningPart;
import com.luckyzz.sporegame.character.CharacterRespawnProcessor;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

class SpectatorCharacterRespawnProcessor implements CharacterRespawnProcessor {

    private final SchedulerRunner runner;
    private final SpawningLocationSelector locationSelector;

    SpectatorCharacterRespawnProcessor(@NotNull final SchedulerRunner runner, @NotNull final SpawningLocationSelector locationSelector) {
        this.runner = runner;
        this.locationSelector = locationSelector;
    }

    @Override
    public @NotNull PlayerRespawnEvent doRespawn(@NotNull final SporeUser user, @NotNull final PlayerRespawnEvent event) {
        final Player player = user.getPlayer();
        event.setRespawnLocation(player.getLocation());
        final SporeCharacterSession session = user.getCharacterSession().get();

        runner.runTaskLater(() -> {
            if(!session.isActive()) {
                return;
            }

            player.setGameMode(GameMode.SPECTATOR);
            runner.runTaskLater(() -> {
                if(!session.isActive()) {
                    return;
                }

                player.setGameMode(GameMode.SURVIVAL);
                session.setLocation(locationSelector.selectLocation(SpawningPart.fromLevels(session.getDetailedLevel().getIndex())).getLocation());

                // Todo msg
                player.teleport(session.getLocation());
                session.getInventory().applyInventory();
            }, SchedulerTicks.toTicks(15, TimeUnit.SECONDS));
        }, 1);
        return event;
    }

}
