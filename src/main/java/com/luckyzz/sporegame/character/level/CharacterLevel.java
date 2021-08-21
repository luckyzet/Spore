package com.luckyzz.sporegame.character.level;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.config.ConfigurationCharacterLevel;
import com.luckyzz.sporegame.config.LevelCheckpoints;
import com.luckyzz.sporegame.util.RomanNumeral;
import org.jetbrains.annotations.NotNull;

public interface CharacterLevel {

    int getIndex();

    default @NotNull String getRomanIndex() {
        return RomanNumeral.toRoman(getIndex());
    }

    @NotNull LevelCheckpoints getCheckpoints();

    @NotNull AkameOptional<LevelCheckpoint> getProgressingCheckpoint();

    @NotNull DNA getCurrentProgress();

    /**
     * @return true if level is need to update and false if not
     */
    boolean changeCurrentProgress(final double delta);

    default boolean changeCurrentProgress(@NotNull final DNA dna) {
        return changeCurrentProgress(dna.getValue());
    }

    default @NotNull DNA getCheckpointProgress() {
        final AkameOptional<LevelCheckpoint> checkpointOptional = getProgressingCheckpoint();
        if(checkpointOptional.isPresent()) {
            final LevelCheckpoint checkpoint = checkpointOptional.get();
            return checkpoint.getMaximal().clone().minusValue(getCurrentProgress());
        }
        return getCurrentProgress();
    }

    @NotNull DNA getProgressLeft();

    @NotNull AkameOptional<ConfigurationCharacterLevel> getNextLevel();

}
