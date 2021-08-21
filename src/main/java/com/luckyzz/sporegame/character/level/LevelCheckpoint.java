package com.luckyzz.sporegame.character.level;

import org.jetbrains.annotations.NotNull;

public interface LevelCheckpoint {

    @NotNull CharacterLevel getLevel();

    int getIndex();

    @NotNull DNA getMinimal();

    @NotNull DNA getMaximal();

    default @NotNull DNA getProgressValue() {
        return getMaximal().clone().minusValue(getMinimal());
    }

    default boolean isDone() {
        return getLevel().getCurrentProgress().getValue() >= getMaximal().getValue();
    }

}
