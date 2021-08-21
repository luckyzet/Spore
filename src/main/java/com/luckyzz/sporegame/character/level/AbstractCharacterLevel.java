package com.luckyzz.sporegame.character.level;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.config.LevelCheckpoints;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCharacterLevel implements CharacterLevel {

    protected final int index;
    protected final LevelCheckpoints checkpoints;

    protected AbstractCharacterLevel(final int index, @NotNull final LevelCheckpoints checkpoints) {
        this.index = index;
        this.checkpoints = checkpoints;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public @NotNull LevelCheckpoints getCheckpoints() {
        return checkpoints;
    }

    @Override
    public @NotNull AkameOptional<LevelCheckpoint> getProgressingCheckpoint() {
        return checkpoints.getProgressing();
    }

    @Override
    public @NotNull DNA getProgressLeft() {
        final DNA left = new DNA(checkpoints.stream()
                .map(LevelCheckpoint::getProgressValue)
                .mapToDouble(DNA::getValue)
                .sum());
        return left.minusValue(getCurrentProgress());
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractCharacterLevel that = (AbstractCharacterLevel) o;
        return new EqualsBuilder()
                .append(index, that.index)
                .append(checkpoints, that.checkpoints)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(index)
                .append(checkpoints)
                .toHashCode();
    }
}
