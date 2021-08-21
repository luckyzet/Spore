package com.luckyzz.sporegame.user;

import com.akamecoder.sporegame.user.statistic.*;
import com.luckyzz.sporegame.user.statistic.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class CacheSporeUserStatistic implements SporeUserStatistic {

    private final SporeUser user;
    private final Map<Statistics, StatisticValue> statisticMap;
    private final SporeUserRepository.StatisticRepository statisticRepository;

    CacheSporeUserStatistic(@NotNull final SporeUser user, @NotNull final Map<Statistics, StatisticValue> statisticMap, @NotNull final SporeUserRepository.StatisticRepository statisticRepository) {
        this.user = user;
        this.statisticMap = statisticMap;
        this.statisticRepository = statisticRepository;
    }

    CacheSporeUserStatistic(@NotNull final SporeUser user, @NotNull final SporeUserRepository.StatisticRepository statisticRepository) {
        this(user, new HashMap<>(), statisticRepository);
    }

    @Override
    public @NotNull StatisticValue getStatistic(@NotNull final Statistics statistics) {
        return statisticMap.getOrDefault(statistics, new EmptyStatisticValue(statistics));
    }

    @Override
    public @NotNull StatisticValue incrementStatistic(@NotNull final Statistics statistics) {
        final StatisticValue value = getStatistic(statistics);
        if(value instanceof IntStatisticValue) {
            final IntStatisticValue intStatisticValue = (IntStatisticValue) value;
            intStatisticValue.setValue(intStatisticValue.intValue() + 1);
        }
        statisticRepository.saveStatistic(user, statistics, value);
        return value;
    }

    @Override
    public @NotNull StatisticValue decrementStatistic(@NotNull final Statistics statistics) {
        final StatisticValue value = getStatistic(statistics);
        if(value instanceof IntStatisticValue) {
            final IntStatisticValue intStatisticValue = (IntStatisticValue) value;
            intStatisticValue.setValue(intStatisticValue.intValue() - 1);
        }
        statisticRepository.saveStatistic(user, statistics, value);
        return value;
    }

}
