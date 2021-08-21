package com.luckyzz.sporegame.character.item;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface CharacterItemStorage extends Iterable<CharacterItem> {

    @NotNull Map<Integer, CharacterItem> getItems();

}
