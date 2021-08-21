package com.luckyzz.sporegame.config;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.character.level.LevelCheckpoint;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class LevelCheckpoints {

    private final Set<LevelCheckpoint> checkpoints;

    LevelCheckpoints() {
        this.checkpoints = new TreeSet<>(Comparator.comparingInt(LevelCheckpoint::getIndex));
    }

    public @NotNull Stream<LevelCheckpoint> stream() {
        return checkpoints.stream();
    }

    public @NotNull AkameOptional<LevelCheckpoint> byIndex(final int index) {
        return AkameOptional.ofOptional(stream()
                .filter(checkpoint -> checkpoint.getIndex() == index)
                .findFirst());
    }

    public @NotNull AkameOptional<LevelCheckpoint> getProgressing() {
        return AkameOptional.ofOptional(stream()
                .filter(checkpoint -> !checkpoint.isDone())
                .findFirst());
    }

    /**
     * @return true if all checkpoints is done and level need to up
     */
    public boolean isDone() {
        return !getProgressing().isPresent();
    }

    boolean add(@NotNull final LevelCheckpoint checkpoint) {
        return checkpoints.add(checkpoint);
    }

}
