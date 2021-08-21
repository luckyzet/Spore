package com.luckyzz.sporegame.config;

import com.akamecoder.cristalix.config.ConfigBuilders;
import com.akamecoder.cristalix.config.SettingConfigYaml;
import com.luckyzz.sporegame.food.FoodSpecifications;
import com.luckyzz.sporegame.food.FoodType;
import com.luckyzz.sporegame.food.category.FoodCategories;
import com.luckyzz.sporegame.food.category.FoodCategory;
import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.world.SporeWorldType;
import com.luckyzz.sporegame.world.logic.EmptyWorldLogic;
import com.luckyzz.sporegame.world.logic.SporeWorldLogic;
import com.luckyzz.sporegame.config.exception.ConfigParseException;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingConfig extends SettingConfigYaml<Settings> {

    private final Map<SporeWorldType, SporeWorld> worldMap = new HashMap<>();
    private final Map<Integer, ConfigurationCharacterLevel> levelMap = new HashMap<>();
    private final Map<FoodCategory, FoodSpecifications> specificationMap = new HashMap<>();

    public SettingConfig(@NotNull final Plugin plugin) {
        super(ConfigBuilders.yaml().setting().plugin(plugin));
        this.reload();
    }

    private void loadWorlds() {
        worldMap.clear();

        final ConfigurationSection section = config.getConfigurationSection("worlds");
        if(section == null) {
            throw new ConfigParseException("The section 'worlds' has not found!");
        }
        section.getKeys(false).forEach(key -> {
            final SporeWorldType type = SporeWorldType.valueOf(key.toUpperCase());
            final String name = section.getString(key);

            final SporeWorld world = new WorldImpl(type, name, new EmptyWorldLogic());
            worldMap.put(type, world);
        });
    }

    private void loadLevels() {
        levelMap.clear();

        final ConfigurationSection section = config.getConfigurationSection("levels");
        if(section == null) {
            throw new ConfigParseException("The section 'levels' has not found!");
        }
        section.getKeys(false).forEach(key -> {
            final int index = Integer.parseInt(key);
            final double checkpointSize = section.getDouble(key + ".checkpointSize");

            final ConfigurationCharacterLevel level = new ConfigurationCharacterLevel(this, index, new LevelCheckpoints());
            final LevelCheckpoints checkpoints = level.getCheckpoints();

            double minimal = 0;
            for(int i = 1; i <= 5; i++) {
                checkpoints.add(new LevelCheckpointImpl(level, i, minimal, minimal + checkpointSize));
                minimal += checkpointSize;
            }

            levelMap.put(index, level);
        });
    }

    private void loadFoodSpecifications() {
        specificationMap.clear();

        final ConfigurationSection section = config.getConfigurationSection("food");
        if(section == null) {
            throw new ConfigParseException("The section 'food' has not found!");
        }
        section.getKeys(false).forEach(key -> {
            final FoodType type = FoodType.fromString(key);
            final ConfigurationSection section1 = section.getConfigurationSection(key);
            section1.getKeys(false).forEach(index -> {
                final int level = Integer.parseInt(index);
                final ConfigurationSection section2 = section1.getConfigurationSection(index);

                final double dna = section2.getDouble("dna");
                final double health = section2.getDouble("health");
                final double scale = section2.getDouble("scale");
                final double speed = section2.getDouble("speed");
                final List<Integer> levels = section2.getIntegerList("levels");

                final FoodCategory category = FoodCategories.fromRaw(type, level);
                specificationMap.put(category, new FoodSpecifications(category, dna, health, scale, speed, levels));
            });
        });
    }

    @Override
    protected void load(@NotNull final YamlConfiguration yaml) {
        loadWorlds();
        loadLevels();
        loadFoodSpecifications();
    }

    public @NotNull ConfigurationSection getDatabaseSection() {
        return config.getConfigurationSection("database");
    }

    public @NotNull SporeWorld getWorld(@NotNull final SporeWorldType type) {
        return worldMap.getOrDefault(type, new EmptyWorld());
    }

    public @NotNull SporeWorld getWorld(@NotNull final World world) {
        return worldMap.values().stream()
                .filter(got -> got.isThatWorld(world))
                .findFirst().orElseGet(EmptyWorld::new);
    }

    public @NotNull SporeWorld getWorld(@NotNull final Player player) {
        return getWorld(player.getWorld());
    }

    public @NotNull ConfigurationCharacterLevel getLevel(final int index) {
        return levelMap.get(index);
    }

    public @NotNull ConfigurationCharacterLevel getFirstLevel() {
        return getLevel(1);
    }

    public void setLogic(@NotNull final SporeWorldType type, @NotNull final SporeWorldLogic logic) {
        final WorldImpl world = (WorldImpl) worldMap.get(type);
        world.setLogic(logic);
        new WorldLogicListener(plugin, world);
    }

    public @NotNull FoodSpecifications getFoodSpecifications(@NotNull final FoodCategory category) {
        return specificationMap.get(category);
    }

    @Override
    public int getInt(@NotNull final Settings path) {
        return super.getInt(path);
    }

}
