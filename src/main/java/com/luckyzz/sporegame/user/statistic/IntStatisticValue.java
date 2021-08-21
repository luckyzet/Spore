package com.luckyzz.sporegame.user.statistic;

import com.luckyzz.sporegame.user.statistic.exception.StatisticException;
import org.jetbrains.annotations.NotNull;

public class IntStatisticValue extends AbstractStatisticValue<Integer> {

    public IntStatisticValue(@NotNull final Statistics statistics, final int value) {
        super(statistics, value);
    }

    public IntStatisticValue(@NotNull final Statistics statistics) {
        super(statistics, 0);
    }

    @Override
    public int intValue() {
        return value;
    }

    public void setValue(final int value) {
        if(value < 0) {
            throw new StatisticException("Statistic value cannot be less than 0. Value is " + value);
        }

        this.value = value;
    }

}
