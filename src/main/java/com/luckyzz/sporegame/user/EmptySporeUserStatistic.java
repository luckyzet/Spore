package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.user.statistic.EmptyStatisticValue;
import com.luckyzz.sporegame.user.statistic.SporeUserStatistic;
import com.luckyzz.sporegame.user.statistic.StatisticValue;
import com.luckyzz.sporegame.user.statistic.Statistics;
import org.jetbrains.annotations.NotNull;

class EmptySporeUserStatistic implements SporeUserStatistic {

    EmptySporeUserStatistic() {
    }

    @Override
    public @NotNull StatisticValue getStatistic(@NotNull final Statistics statistics) {
        return new EmptyStatisticValue(statistics);
    }

    @Override
    public @NotNull StatisticValue incrementStatistic(@NotNull final Statistics statistics) {
        return new EmptyStatisticValue(statistics);
    }

    @Override
    public @NotNull StatisticValue decrementStatistic(@NotNull final Statistics statistics) {
        return new EmptyStatisticValue(statistics);
    }

}
