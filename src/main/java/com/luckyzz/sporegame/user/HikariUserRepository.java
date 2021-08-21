package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.database.AkameDatabaseService;
import com.akamecoder.cristalix.database.HikariDatabase;
import com.akamecoder.cristalix.database.loader.DatabaseLoaderType;
import com.akamecoder.cristalix.util.player.PlayerFilters;
import com.akamecoder.cristalix.util.position.Position;
import com.akamecoder.cristalix.util.position.serialize.PositionSerialize;
import com.akamecoder.cristalix.util.position.serialize.PositionStringSerialize;
import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.world.SporeWorldType;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacter;
import com.luckyzz.sporegame.config.SettingConfig;
import com.luckyzz.sporegame.config.Settings;
import com.luckyzz.sporegame.user.exception.UserException;
import com.akamecoder.sporegame.user.statistic.*;
import com.luckyzz.sporegame.user.statistic.exception.StatisticException;
import com.luckyzz.sporegame.user.statistic.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;

class HikariUserRepository implements SporeUserRepository {

    private static final PositionStringSerialize position = PositionSerialize.base64();

    private final SettingConfig config;
    private final HikariDatabase database;

    private final StatisticRepository statisticRepository;
    private final CharacterRepository characterRepository;

    HikariUserRepository(@NotNull final Plugin plugin, @NotNull final AkameDatabaseService databaseService, @NotNull final SettingConfig config) {
        this.config = config;

        database = databaseService.getLoader(plugin, DatabaseLoaderType.YAML).load(config.getDatabaseSection());

        database.sync().update("CREATE TABLE IF NOT EXISTS `users` (" +
                "`uuid` VARCHAR(60) NOT NULL, " +
                "`name` VARCHAR(32) NOT NULL, " +
                "`maximumCharacters` INT NOT NULL, " +
                "`world` VARCHAR(32) NOT NULL" +
                ");");
        database.sync().update("CREATE TABLE IF NOT EXISTS `characters` (" +
                "`uuid` VARCHAR(60) NOT NULL, " +
                "`name` VARCHAR(16) NOT NULL, " +
                "`direction` VARCHAR(16) NOT NULL, " +
                "`location` TEXT NOT NULL" +
                ");");
        database.sync().update("CREATE TABLE IF NOT EXISTS `levels` (" +
                "`name` VARCHAR(16) NOT NULL, " +
                "`index` INT NOT NULL, " +
                "`progress` REAL NOT NULL" +
                ");");
        database.sync().update("CREATE TABLE IF NOT EXISTS `specifications` (" +
                "`name` VARCHAR(60) NOT NULL, " +
                "`scale` REAL NOT NULL, " +
                "`speed` REAL NOT NULL, " +
                "`health` REAL NOT NULL, " +
                "`damage` REAL NOT NULL" +
                ");");
        database.sync().update("CREATE TABLE IF NOT EXISTS `statistic` (" +
                "`uuid` VARCHAR(60) NOT NULL, " +
                "`statistics` VARCHAR(16) NOT NULL, " +
                "`value` TEXT NOT NULL" +
                ");");

        statisticRepository = new StatisticRepositoryImpl();
        characterRepository = new CharacterRepositoryImpl();
    }

    @Override
    public @NotNull CompletableFuture<SporeUser> loadUser(@NotNull final String name) {
        final CompletableFuture<SporeUser> future = new CompletableFuture<>();
        database.async().result("SELECT * FROM users WHERE name = ?;", result -> {
            if(!result.next()) {
                final Player player = PlayerFilters.getPlayer(name).orElseThrow(() -> {
                    return new UserException("Player is not online!");
                });
                final SporeUser user = new SporeUserImpl(config.getWorld(SporeWorldType.SPAWN), player.getUniqueId(), name, user1 -> new SporeCharactersImpl(user1, config.getInt(Settings.CHARACTERS_COUNT), new SporeCharacterMap()), user1 -> {
                    final Map<Statistics, StatisticValue> map = new HashMap<>();
                    for (final Statistics statistics : Statistics.values()) {
                        if(statistics.getType() == StatisticType.INT) {
                            final StatisticValue statisticValue = new IntStatisticValue(statistics);
                            map.put(statistics, statisticValue);
                            database.sync().update("INSERT INTO statistic VALUES (?, ?, ?);", player.getUniqueId().toString(),
                                    statistics.name(), statisticValue.intValue());
                        }
                    }
                    return new CacheSporeUserStatistic(user1, map, statisticRepository);
                });

                future.complete(user);
                database.sync().update("INSERT INTO users VALUES (?, ?, ?, ?);", user.getUniqueId().toString(),
                        user.getName(), user.getCharacters().getMaximumCharacterCount(), user.getWorld().getType().name());
                return;
            }
            final UUID uuid = UUID.fromString(result.getString("uuid"));
            final int maximumCharacters = result.getInt("maximumCharacters");
            final SporeWorld world = config.getWorld(SporeWorldType.fromString(result.getString("world")));

            final SporeUser user = new SporeUserImpl(world, uuid, name, user1 -> {
                final SporeCharacterMap characterMap = new SporeCharacterMap();
                database.sync().result("SELECT name, direction FROM characters WHERE uuid = ?;", result1 -> {
                    while (result1.next()) {
                        final String characterName = result1.getString("name");
                        final CharacterDirection direction = CharacterDirection.fromString(result1.getString("direction"));

                        final CompletableFuture<Integer> level = new CompletableFuture<>();
                        database.sync().result("SELECT index FROM levels WHERE name = ?;", result2 -> {
                            level.complete(result2.getInt("index"));
                        }, characterName);

                        characterMap.put(new SporeCharacterImpl(characterRepository, user1, characterName, direction, level.join()));
                    }
                }, uuid.toString());

                return new SporeCharactersImpl(user1, maximumCharacters, characterMap);
            }, user1 -> {
                final Map<Statistics, StatisticValue> map = new HashMap<>();

                database.sync().result("SELECT * FROM statistic WHERE uuid = ?;", result1 -> {
                    while (result1.next()) {
                        final Statistics statistics = Statistics.valueOf(result1.getString("statistics"));
                        if(statistics.getType() == StatisticType.INT) {
                            map.put(statistics, new IntStatisticValue(statistics, result1.getInt("value")));
                            continue;
                        }

                        new StatisticException("Statistic type has not registered in repository, so getting empty!").printStackTrace();
                        map.put(statistics, new EmptyStatisticValue(statistics));
                    }

                }, user1.getUniqueId().toString());

                return new CacheSporeUserStatistic(user1, map, statisticRepository);
            });

            future.complete(user);
        }, name);
        return future;
    }

    @Override
    public void saveWorld(@NotNull final SporeUser user) {
        database.async().update("UPDATE users SET `world` = ? WHERE `uuid` = ?;",
                user.getWorld().getType().name(), user.getUniqueId().toString());
    }

    private class StatisticRepositoryImpl implements StatisticRepository {

        private StatisticRepositoryImpl() {
        }

        @Override
        public void saveStatistic(@NotNull final SporeUser user, @NotNull final Statistics statistics, @NotNull final StatisticValue value) {
            if(statistics.getType() == StatisticType.INT) {
                database.async().update("UPDATE statistic SET `value` = ? WHERE `uuid` = ? AND `statistics` = ?;",
                        value.intValue(), user.getUniqueId().toString(), statistics.name());
            }
        }

    }

    @Override
    public @NotNull StatisticRepository statisticRepository() {
        return statisticRepository;
    }

    private class CharacterRepositoryImpl implements CharacterRepository {

        private final Set<String> characterNames = new HashSet<>();

        private CharacterRepositoryImpl() {
            database.async().result("SELECT name FROM characters;", result -> {
                while (result.next()) {
                    characterNames.add(result.getString("name"));
                }
            });
        }

        @Override
        public void saveMaximumCharacters(@NotNull final SporeUser user) {
            database.async().update("UPDATE characters SET `maximumCharacters` = ? WHERE `uuid` = ?;",
                    user.getCharacters().getMaximumCharacterCount(), user.getUniqueId().toString());
        }

        @Override
        public @NotNull CompletableFuture<Boolean> checkCharacterName(@NotNull final String name) {
            return CompletableFuture.completedFuture(characterNames.contains(name));
        }

        @Override
        public @NotNull CompletableFuture<DetailedSporeCharacter> loadDetailed(@NotNull final SporeUser user, @NotNull final SporeCharacter character) {
            final CompletableFuture<DetailedSporeCharacter> future = new CompletableFuture<>();

            return future;
        }

        @Override
        public void saveCharacterLocation(@NotNull final DetailedSporeCharacter character) {
            database.async().update("UPDATE characters SET `location` = ? WHERE `uuid` = ?;",
                    position.serialize(Position.createPosition(character.getLocation())), character.getUser().getUniqueId().toString());
        }

        @Override
        public void createCharacter(@NotNull final DetailedSporeCharacter character) {
            database.async().update("INSERT INTO characters VALUES (?, ?, ?, ?);", character.getUser().getUniqueId().toString(),
                    character.getName(),
                    character.getDirection().name(), position.serialize(Position.createPosition(character.getLocation())));
            database.async().update("INSERT INTO levels VALUES (? ,? ,?);", character.getName(), character.getDetailedLevel().getIndex(),
                    character.getDetailedLevel().getCurrentProgress().getValue());
        }

    }

    @Override
    public @NotNull CharacterRepository characterRepository() {
        return characterRepository;
    }

    @Override
    public void cancel() {
        database.close();
    }

}
