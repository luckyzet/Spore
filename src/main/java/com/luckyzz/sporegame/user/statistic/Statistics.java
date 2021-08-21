package com.luckyzz.sporegame.user.statistic;

import com.akamecoder.cristalix.api.Typable;
import org.jetbrains.annotations.NotNull;

public enum Statistics implements Typable<StatisticType> {

    KILLS(StatisticType.INT),
    FOOD_EATEN(StatisticType.INT);

    private final StatisticType type;

    Statistics(@NotNull final StatisticType type) {
        this.type = type;
    }

    @Override
    public @NotNull StatisticType getType() {
        return type;
    }

}
