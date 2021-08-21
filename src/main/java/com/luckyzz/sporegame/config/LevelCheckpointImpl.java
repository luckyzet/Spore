package com.luckyzz.sporegame.config;

import com.luckyzz.sporegame.character.level.CharacterLevel;
import com.luckyzz.sporegame.character.level.DNA;
import com.luckyzz.sporegame.character.level.LevelCheckpoint;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class LevelCheckpointImpl implements LevelCheckpoint {

    private final CharacterLevel level;
    private final int index;
    private final DNA minimal, maximal;

    LevelCheckpointImpl(@NotNull final CharacterLevel level, final int index, final double minimal, final double maximal) {
        this.level = level;
        this.index = index;
        this.minimal = new DNA(minimal);
        this.maximal = new DNA(maximal);
    }

    @Override
    public @NotNull CharacterLevel getLevel() {
        return level;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public @NotNull DNA getMinimal() {
        return minimal;
    }

    @Override
    public @NotNull DNA getMaximal() {
        return maximal;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LevelCheckpointImpl that = (LevelCheckpointImpl) o;
        return new EqualsBuilder()
                .append(index, that.index)
                .append(minimal, that.minimal)
                .append(maximal, that.maximal)
                .append(level, that.level)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(level)
                .append(index)
                .append(minimal)
                .append(maximal)
                .toHashCode();
    }
}
