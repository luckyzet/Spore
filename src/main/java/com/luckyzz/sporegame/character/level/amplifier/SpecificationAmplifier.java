package com.luckyzz.sporegame.character.level.amplifier;

import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import org.jetbrains.annotations.NotNull;

public interface SpecificationAmplifier {

    /**
     * @return the level per which amplifier is applying for user
     */
    int getPerLevel();

    @NotNull CharacterSpecifications getSpecifications(@NotNull final CharacterDirection direction);

}
