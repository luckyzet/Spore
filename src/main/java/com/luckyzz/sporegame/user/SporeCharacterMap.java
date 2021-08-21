package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.character.SporeCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class SporeCharacterMap {

    private final Map<Integer, SporeCharacter> characterMap;

    SporeCharacterMap(@NotNull final Map<Integer, SporeCharacter> characterMap) {
        this.characterMap = characterMap;
    }

    SporeCharacterMap() {
        this(new HashMap<>());
    }

    public int size() {
        return characterMap.size();
    }

    public @NotNull AkameOptional<SporeCharacter> byIndex(final int index) {
        return AkameOptional.ofNullable(characterMap.get(index));
    }

    public @NotNull Collection<SporeCharacter> characters() {
        return characterMap.values();
    }

    private int calculateNextIndex() {
        int index = characterMap.keySet().stream().sorted(Comparator.comparingInt(Integer::intValue)).limit(1).findFirst().orElse(-1);
        return ++index;
    }

    void put(@NotNull final SporeCharacter character) {
        characterMap.put(calculateNextIndex(), character);
    }

}
