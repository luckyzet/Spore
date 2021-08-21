package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.scheduler.Scheduler;
import com.akamecoder.cristalix.scheduler.SchedulerTicks;
import com.akamecoder.cristalix.util.player.PlayerFilters;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

final class RegularCharacterLocationTask extends Scheduler {

    private final SporeUserService userService;
    private final SporeUserRepository.CharacterRepository characterRepository;

    RegularCharacterLocationTask(@NotNull final Plugin plugin, @NotNull final SporeUserService userService,
                                 @NotNull final SporeUserRepository.CharacterRepository characterRepository) {
        super(plugin);
        this.userService = userService;
        this.characterRepository = characterRepository;
        this.runTaskTimerAsynchronously(SchedulerTicks.toTicks(30, TimeUnit.SECONDS));
    }

    @Override
    public void run() {
        PlayerFilters.online().forEach(player -> {
            final SporeUser user = userService.getUser(player);
            user.getCharacterSession().ifPresent(session -> {
                session.setLocation(player.getLocation());
                characterRepository.saveCharacterLocation(session);
            });
        });
    }

}
