package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.scheduler.Scheduler;
import com.akamecoder.cristalix.scheduler.SchedulerTicks;
import com.luckyzz.sporegame.character.CharacterLevelUpdateProcessor;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.config.ConfigurationCharacterLevel;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

class AnimationCharacterLevelProcessor implements CharacterLevelUpdateProcessor {

    private final Plugin plugin;

    AnimationCharacterLevelProcessor(@NotNull final Plugin plugin) {
        this.plugin = plugin;
    }

    private class AnimationScheduler extends Scheduler {

        private final Player player;
        private int times = 5;

        private AnimationScheduler(@NotNull final SporeCharacterSession session) {
            super(AnimationCharacterLevelProcessor.this.plugin);
            this.player = session.getUser().getPlayer();
            this.r = 0.01 * session.getSpecifications().getScale();
            this.runTaskTimer(SchedulerTicks.SECOND);
        }

        private final double r;

        @Override
        public void run() {
            if(!player.isOnline() || times-- <= 0) {
                cancel();
                return;
            }

            final Location location = player.getLocation().clone().add(0, r, 0);

            for (int degree = 0; degree < 360; degree++) {
                final double radians = Math.toRadians(degree);
                final double x = r * Math.cos(radians);
                final double z = r * Math.sin(radians);

                location.add(x, 0, z);
                Particle.REDSTONE.builder()
                        .location(location)
                        .color(Color.ORANGE)
                        .allPlayers()
                        .offset(0, 0, 0)
                        .spawn();
            }
        }

    }

    @Override
    public void update(@NotNull final SporeCharacterSession session, @NotNull final ConfigurationCharacterLevel next) {
        final Player player = session.getUser().getPlayer();
        player.sendTitle(ChatColor.GREEN + "Ваш уровень повышен!", "", 20, 40, 20);
        player.sendMessage(SporeMessages.SUCCESS_PREFIX + "Ваш уровень повышен до " + SporeMessages.HIGHLIGHTED_PREFIX + next.getIndex() + SporeMessages.DEFAULT_PREFIX + "!");
        new AnimationScheduler(session);
    }

}
