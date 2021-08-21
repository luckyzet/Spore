package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.character.item.CharacterItem;
import com.luckyzz.sporegame.character.item.CharacterItemStorage;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class MapCharacterItemStorage implements CharacterItemStorage {

    private final Map<Integer, CharacterItem> map;

    MapCharacterItemStorage(@NotNull final Map<Integer, CharacterItem> map) {
        this.map = map;
    }

    MapCharacterItemStorage() {
        this(new HashMap<>());
    }

    @Override
    public @NotNull Map<Integer, CharacterItem> getItems() {
        return map;
    }

    @Override
    public @NotNull Iterator<CharacterItem> iterator() {
        return map.values().iterator();
    }

}
