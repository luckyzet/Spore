package com.luckyzz.sporegame.spawning;

import com.akamecoder.cristalix.config.ConfigurationUtil;
import com.akamecoder.cristalix.scheduler.SchedulerRunner;
import com.akamecoder.cristalix.util.position.Position;
import com.akamecoder.cristalix.util.position.serialize.PositionSerialize;
import com.akamecoder.cristalix.util.position.serialize.YamlPositionCurrentConfigSerialize;
import com.luckyzz.sporegame.world.SpawningPart;
import com.luckyzz.sporegame.util.Log;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

class YamlSpawningLocationRepository implements SpawningLocationRepository {

    private final SchedulerRunner runner;
    private final File file;
    private final YamlConfiguration configuration;

    private final YamlPositionCurrentConfigSerialize positionSerialize;

    YamlSpawningLocationRepository(@NotNull final SpawningLocationStorage storage, @NotNull final SchedulerRunner runner, @NotNull final File file) {
        this.runner = runner;
        this.file = file;

        configuration = YamlConfiguration.loadConfiguration(file);
        positionSerialize = PositionSerialize.yaml(configuration);

        final ConfigurationSection section = configuration.getConfigurationSection("spawnings");
        if(section == null) {
            return;
        }
        section.getKeys(false).forEach(key -> {
            final SpawningPart part = SpawningPart.fromIndex(section.getInt(key + ".part"));
            final Location location = positionSerialize.deserialize("spawnings." + key + ".location").toLocation();
            final SpawningLocation spawningLocation = new SpawningLocationImpl(part, location);
            storage.put(spawningLocation);
        });
    }

    @Override
    public void saveSpawningLocation(@NotNull final SpawningLocation location) {
        runner.runTaskAsync(() -> {
            final ConfigurationSection section = ConfigurationUtil.getSection(configuration, "spawnings");

            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            while (section.isConfigurationSection(uuidString)) {
                uuid = UUID.randomUUID();
                uuidString = uuid.toString();
            }

            section.set(uuidString + ".part", location.getPart().getIndex());
            positionSerialize.serialize("spawnings." + uuidString + ".location", Position.createPosition(location.getLocation()));

            try {
                configuration.save(file);
            } catch (final IOException exception) {
                Log.warn("Error was occurred while saving spawning location! Location was not saved!");
                exception.printStackTrace();
            }
        });
    }

    @Override
    public void cancel() {
        try {
            configuration.save(file);
        } catch (final IOException exception) {
            Log.warn("Error was occurred while saving spawning location! Location was not saved!");
            exception.printStackTrace();
        }
    }

}
