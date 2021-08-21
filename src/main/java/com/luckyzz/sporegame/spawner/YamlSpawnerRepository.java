package com.luckyzz.sporegame.spawner;

import com.akamecoder.cristalix.scheduler.SchedulerRunner;
import com.akamecoder.cristalix.util.position.Position;
import com.akamecoder.cristalix.util.position.serialize.PositionSerialize;
import com.akamecoder.cristalix.util.position.serialize.YamlPositionCurrentConfigSerialize;
import com.luckyzz.sporegame.food.FoodType;
import com.luckyzz.sporegame.food.SporeFoodProcessor;
import com.luckyzz.sporegame.food.category.FoodCategories;
import com.luckyzz.sporegame.food.category.FoodCategory;
import com.luckyzz.sporegame.util.Log;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class YamlSpawnerRepository implements SpawnerRepository {

    private final SchedulerRunner runner;
    private final File file;
    private final YamlConfiguration configuration;

    private final YamlPositionCurrentConfigSerialize positionSerialize;

    private final Map<SpawnerType, SpawnerStrategy> strategyMap = new HashMap<>();

    YamlSpawnerRepository(@NotNull final SporeFoodProcessor foodProcessor, @NotNull final SpawnerMap spawnerMap, @NotNull final SchedulerRunner runner, @NotNull final File file) {
        this.runner = runner;
        this.file = file;

        configuration = YamlConfiguration.loadConfiguration(file);
        positionSerialize = PositionSerialize.yaml(configuration);

        strategyMap.put(SpawnerType.RANDOM_DELAYED, new RandomDelayedStrategy());

        final ConfigurationSection section = configuration.getConfigurationSection("spawners");
        if(section == null) {
            return;
        }

        section.getKeys(false).forEach(key -> {
            final ConfigurationSection current = section.getConfigurationSection(key);
            final SpawnerType type = SpawnerType.valueOf(current.getString("type"));
            final SpawnerStrategy strategy = strategyMap.get(type);
            spawnerMap.putSpawner(strategy.load(foodProcessor, current));
        });
    }

    private interface SpawnerStrategy {

        @NotNull Spawner load(@NotNull final SporeFoodProcessor foodProcessor, @NotNull final ConfigurationSection section);

        void save(@NotNull final ConfigurationSection section, @NotNull final Spawner spawner);

    }

    private class RandomDelayedStrategy implements SpawnerStrategy {

        @Override
        public @NotNull Spawner load(@NotNull final SporeFoodProcessor foodProcessor, @NotNull final ConfigurationSection section) {
            final String name = section.getName();
            final Location location = positionSerialize.deserialize(section.getCurrentPath() + ".location").toLocation();

            final FoodType foodType = FoodType.valueOf(section.getString("category.type"));
            final int level = section.getInt("category.level");
            final FoodCategory category = FoodCategories.fromRaw(foodType, level);

            final int minDelay = section.getInt("minDelay");
            final int maxDelay = section.getInt("maxDelay");
            final int distance = section.getInt("distance");

            return new RandomDelayedSpawner(foodProcessor, name, category, location, minDelay, maxDelay, distance);
        }

        @Override
        public void save(@NotNull final ConfigurationSection section, @NotNull final Spawner spawnerRaw) {
            final RandomDelayedSpawner spawner = (RandomDelayedSpawner) spawnerRaw;
            section.set("type", spawner.getType().name());
            section.set("category.type", spawner.getCategory().getType().name());
            section.set("category.level", spawner.getCategory().getLevel());
            positionSerialize.serialize(section.getCurrentPath() + ".location", Position.createPosition(spawner.getLocation()));
            section.set("minDelay", spawner.getMinDelay());
            section.set("maxDelay", spawner.getMaxDelay());
            section.set("distance", spawner.getDistance());
        }

    }

    @Override
    public void saveSpawner(@NotNull final Spawner spawner) {
        runner.runTaskAsync(() -> {
            final SpawnerStrategy strategy = strategyMap.get(spawner.getType());
            final ConfigurationSection section = configuration.createSection("spawners." + spawner.getName());

            strategy.save(section, spawner);

            try {
                configuration.save(file);
            } catch (final IOException exception) {
                Log.warn("Error was occurred while saving spawner! Spawner was not saved!");
                exception.printStackTrace();
            }
        });
    }

    @Override
    public void cancel() {
        try {
            configuration.save(file);
        } catch (final IOException exception) {
            Log.warn("Error was occurred while saving spawners! Spawners was not saved!");
            exception.printStackTrace();
        }
    }

}
