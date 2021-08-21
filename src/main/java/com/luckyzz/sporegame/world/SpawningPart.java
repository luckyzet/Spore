package com.luckyzz.sporegame.world;

import com.luckyzz.sporegame.character.level.CharacterLevel;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public enum SpawningPart {

    SPAWN(0, SporeWorldType.SPAWN),
    FIRST(1, SporeWorldType.ARENA, 1,2,3,4,5,6,7,8,9,10),
    SECOND(2, SporeWorldType.ARENA, 11,12,13,14,15,16,17,18,19,20),
    THIRD(3, SporeWorldType.ARENA, 21,22,23,24,25,26,27,28,29,30),
    FOURTH(4, SporeWorldType.ARENA, 31,32,33,34,35,36,37,38,39,40);

    private final int index;
    private final SporeWorldType type;
    private final List<Integer> levels;

    SpawningPart(final int index, @NotNull final SporeWorldType type, @NotNull final Integer... levels) {
        this.index = index;
        this.type = type;
        this.levels = Arrays.asList(levels);
    }

    public static @NotNull SpawningPart fromIndex(final int index) {
        for (final SpawningPart value : values()) {
            if(value.index == index) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

    public static @NotNull SpawningPart fromLevels(final int index) {
        for (final SpawningPart value : values()) {
            if(value.levels.contains(index)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getIndex() {
        return index;
    }

    public @NotNull SporeWorldType getWorldType() {
        return type;
    }

    public @NotNull List<Integer> getLevels() {
        return levels;
    }

    public boolean isSpawningPart(@NotNull final CharacterLevel level) {
        return levels.contains(level.getIndex());
    }

}
