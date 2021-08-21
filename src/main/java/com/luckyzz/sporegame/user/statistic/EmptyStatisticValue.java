package com.luckyzz.sporegame.user.statistic;

import org.jetbrains.annotations.NotNull;

public class EmptyStatisticValue extends AbstractStatisticValue<Object> {

    public EmptyStatisticValue(@NotNull final Statistics statistics) {
        super(statistics, -1);
    }

    @Override
    public int intValue() {
        return -1;
    }

}
