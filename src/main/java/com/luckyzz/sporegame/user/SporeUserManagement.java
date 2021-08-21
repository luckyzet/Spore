package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.api.Cancelable;
import com.akamecoder.cristalix.database.AkameDatabaseService;
import com.akamecoder.cristalix.scheduler.Scheduler;
import com.akamecoder.cristalix.scheduler.SchedulerRunner;
import com.luckyzz.sporegame.spawning.SpawningLocationManagement;
import com.luckyzz.sporegame.spawning.SpawningLocationSelector;
import com.luckyzz.sporegame.world.SpawningPart;
import com.luckyzz.sporegame.world.SporeWorldType;
import com.luckyzz.sporegame.world.logic.SpawnWorldLogic;
import com.luckyzz.sporegame.character.SporeCharacterFactory;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import com.luckyzz.sporegame.character.session.CharacterSessionCleaner;
import com.luckyzz.sporegame.character.session.CharacterSessionFactory;
import com.luckyzz.sporegame.config.SettingConfig;
import com.luckyzz.sporegame.user.join.JoinHandleStrategyExecutor;
import com.luckyzz.sporegame.user.statistic.StatisticEventHandleRegistryImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class SporeUserManagement implements Cancelable {

    private final SporeUserRepository repository;
    private final SporeUserService userService;
    private final SporeCharacterFactory characterFactory;
    private final CharacterSessionFactory sessionFactory;
    private final CharacterSessionCleaner sessionCleaner;
    private final Scheduler characterLocationTask;

    public SporeUserManagement(@NotNull final Plugin plugin, @NotNull final AkameDatabaseService databaseService, @NotNull final SchedulerRunner runner, @NotNull final SettingConfig config,
                               @NotNull final SpawningLocationManagement locationManagement) {
        final SpawningLocationSelector locationSelector = locationManagement.getSelector();

        repository = new HikariUserRepository(plugin, databaseService, config);
        //repository = new EmptySporeUserRepository(config);
        userService = new CacheSporeUserService(repository::loadUser);
        characterFactory = new SporeCharacterFactoryImpl(config, repository.characterRepository(), locationManagement.getSelector());
        sessionFactory = new PlayingCharacterSessionFactory(config.getWorld(SporeWorldType.ARENA), repository.characterRepository());
        sessionCleaner = new PlayingCharacterSessionCleaner(config.getWorld(SporeWorldType.SPAWN), repository.characterRepository());

        characterLocationTask = new RegularCharacterLocationTask(plugin, userService, repository.characterRepository());

        new UserJoinQuitListener(plugin, runner, userService, sessionCleaner);
        new StatisticEventHandleRegistryImpl().registry(plugin, userService);

        new WorldChangingListener(plugin, repository);
        new LevelCharacterListener(plugin, new AnimationCharacterLevelProcessor(plugin));

        new JoinHandleStrategyExecutor(plugin, EventPriority.MONITOR, user -> {
            final Player player = user.getPlayer();
            CharacterSpecifications.SPAWN_DEFAULT.applyTo(player);
            if(user.getWorld().getType() == SporeWorldType.SPAWN) {
                return;
            }
            locationSelector.selectLocation(SpawningPart.SPAWN).teleport(player);
            user.setWorld(config.getWorld(SporeWorldType.SPAWN));
            repository.saveWorld(user);
        });

        config.setLogic(SporeWorldType.SPAWN, new SpawnWorldLogic(locationSelector));
        config.setLogic(SporeWorldType.ARENA, new ArenaWorldLogic(userService, new CharacterDeathProcessorImpl(),
                new SpectatorCharacterRespawnProcessor(runner, locationSelector)));
    }

    public @NotNull SporeUserRepository getRepository() {
        return repository;
    }

    public @NotNull SporeUserService getUserService() {
        return userService;
    }

    public @NotNull CharacterSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public @NotNull SporeCharacterFactory getCharacterFactory() {
        return characterFactory;
    }

    public @NotNull CharacterSessionCleaner getSessionCleaner() {
        return sessionCleaner;
    }

    @Override
    public void cancel() {
        repository.cancel();
        characterLocationTask.cancel();
    }

}
