package com.luckyzz.sporegame.character.level.amplifier;

import com.akamecoder.cristalix.util.KeyValue;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.level.CharacterLevel;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import com.luckyzz.sporegame.character.level.amplifier.exception.SpecificationException;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class SpecificationAmplifiers {

    private static class SpecificationAmplifierImpl implements SpecificationAmplifier {

        private final int perLevel;
        private final Map<CharacterDirection, CharacterSpecifications> specifications;

        @SafeVarargs
        SpecificationAmplifierImpl(final int perLevel, @NotNull final KeyValue<CharacterDirection, CharacterSpecifications>... specifications) {
            this.perLevel = perLevel;
            this.specifications = Arrays.stream(specifications).collect(Collectors.toMap(KeyValue::getKey, keyValue -> {
                if(keyValue.getValue() == null) {
                    throw new SpecificationException("Got key is null");
                }
                return keyValue.getValue();
            }));
        }

        @Override
        public int getPerLevel() {
            return perLevel;
        }

        @Override
        public @NotNull CharacterSpecifications getSpecifications(@NotNull final CharacterDirection direction) {
            return specifications.getOrDefault(direction, new CharacterSpecifications(0, 0, 0, 0));
        }
    }

    private SpecificationAmplifiers() {
        throw new UnsupportedOperationException();
    }

    private static final SpecificationAmplifier PER_EVERY_LEVEL = new SpecificationAmplifierImpl(1,
            new KeyValue<>(CharacterDirection.HERBIVORES, new CharacterSpecifications(0.1, 0.12, 1, 0)),
            new KeyValue<>(CharacterDirection.CARNIVORES, new CharacterSpecifications(0.1, 0.1, 1, 0))
    );

    private static final SpecificationAmplifier PER_EVERY_FIFTH_LEVEL = new SpecificationAmplifierImpl(5,
            new KeyValue<>(CharacterDirection.HERBIVORES, new CharacterSpecifications(0, 0, 0, 1)),
            new KeyValue<>(CharacterDirection.CARNIVORES, new CharacterSpecifications(0, 0, 0, 1.5))
    );

    public static @NotNull SpecificationAmplifier calculateAmplifier(@NotNull final CharacterLevel level) {
        final int index = level.getIndex();
        if((index % 5) == 0) {
            return PER_EVERY_FIFTH_LEVEL;
        }
        return PER_EVERY_LEVEL;
    }

}
