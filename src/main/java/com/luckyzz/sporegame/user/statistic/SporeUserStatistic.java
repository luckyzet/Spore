package com.luckyzz.sporegame.user.statistic;

import org.jetbrains.annotations.NotNull;

public interface SporeUserStatistic {

    @NotNull StatisticValue getStatistic(@NotNull final Statistics statistics);

    @NotNull StatisticValue incrementStatistic(@NotNull final Statistics statistics);

    @NotNull StatisticValue decrementStatistic(@NotNull final Statistics statistics);

}
