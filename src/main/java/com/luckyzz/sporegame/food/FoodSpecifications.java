package com.luckyzz.sporegame.food;

import com.akamecoder.cristalix.api.Typable;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import com.luckyzz.sporegame.character.level.DNA;
import com.luckyzz.sporegame.character.level.amplifier.SpecificationAmplifier;
import com.luckyzz.sporegame.food.category.FoodCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FoodSpecifications extends CharacterSpecifications implements Typable<FoodType> {

    private final FoodCategory category;
    private final DNA dna;

    private final List<Integer> levels;

    public FoodSpecifications(@NotNull final FoodCategory category, final double dna, final double health, final double scale,
                              final double speed, @NotNull final List<Integer> levels) {
        super(scale, speed, health, 0);
        this.category = category;
        this.dna = new DNA(dna);
        this.health = health;
        this.scale = scale;
        this.speed = speed;
        this.levels = levels;
    }

    @Override
    public @NotNull CharacterSpecifications amplify(@NotNull final SpecificationAmplifier amplifier, @NotNull final CharacterDirection direction) {
        throw new UnsupportedOperationException();
    }

    public @NotNull FoodCategory getCategory() {
        return category;
    }

    @Override
    public @NotNull FoodType getType() {
        return category.getType();
    }

    public int getLevel() {
        return category.getLevel();
    }

    public @NotNull DNA getDna() {
        return dna;
    }

    public @NotNull List<Integer> getLevels() {
        return levels;
    }
}
