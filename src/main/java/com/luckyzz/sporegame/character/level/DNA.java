package com.luckyzz.sporegame.character.level;

import org.jetbrains.annotations.NotNull;

/**
 * Wrapper class for measurement DNA
 */
public class DNA implements Cloneable {

    private double value;

    public DNA(final double value) {
        this.value = value;
    }

    /**
     * @return simple double DNA value
     */
    public double getValue() {
        return value;
    }

    public @NotNull DNA setValue(final double value) {
        this.value = value;
        return this;
    }

    public @NotNull DNA plusValue(final double value) {
        this.value += value;
        return this;
    }

    public @NotNull DNA minusValue(final double value) {
        this.value -= value;
        return this;
    }

    public @NotNull DNA shareValue(final double value) {
        this.value /= value;
        return this;
    }

    public @NotNull DNA setValue(@NotNull final DNA value) {
        return setValue(value.getValue());
    }

    public @NotNull DNA plusValue(@NotNull final DNA value) {
        return plusValue(value.getValue());
    }

    public @NotNull DNA minusValue(@NotNull final DNA value) {
        return minusValue(value.getValue());
    }

    public @NotNull DNA shareValue(@NotNull final DNA value) {
        return shareValue(value.getValue());
    }

    @Override
    public @NotNull DNA clone() {
        return new DNA(value);
    }

}
