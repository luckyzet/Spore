package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.spawning.SpawningLocationSelector;
import com.luckyzz.sporegame.world.SpawningPart;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacterFactory;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.config.SettingConfig;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

class SporeCharacterFactoryImpl implements SporeCharacterFactory {

    private final SettingConfig config;
    private final SporeUserRepository.CharacterRepository repository;
    private final SpawningLocationSelector locationSelector;

    SporeCharacterFactoryImpl(@NotNull final SettingConfig config, @NotNull final SporeUserRepository.CharacterRepository repository,
                              @NotNull final SpawningLocationSelector locationSelector) {
        this.config = config;
        this.repository = repository;
        this.locationSelector = locationSelector;
    }

    @Override
    public @NotNull DetailedSporeCharacter createCharacter(@NotNull final SporeUser user, @NotNull final CharacterDirection direction,
                                                           @NotNull final String name) {
        final DetailedSporeCharacter character = new DetailedSporeCharacterImpl(repository, user, name, direction,
                locationSelector.selectLocation(SpawningPart.FIRST).getLocation(), direction.getSpecifications(),
                got -> config.getFirstLevel().toProgressing(got),
                new MapCharacterItemStorage(), new LoadedCharacterInventory(user, new HashMap<>()));
        final SporeCharacters characters = user.getCharacters();
        characters.addCharacter(character);
        //repository.createCharacter(character);
        // TODO: Save repo get first level and fill inventory with default items
        return character;
    }

}
