package com.luckyzz.sporegame.user.statistic;

import org.jetbrains.annotations.NotNull;

public interface StatisticValue {

    @NotNull Statistics getStatistics();

    int intValue();

}
