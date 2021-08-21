package com.luckyzz.sporegame.user.statistic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class AbstractStatisticValue<T> implements StatisticValue {

    final Statistics statistics;
    T value;

    AbstractStatisticValue(@NotNull final Statistics statistics, @NotNull final T value) {
        this.statistics = statistics;
        this.value = value;
    }

    @Override
    public @NotNull Statistics getStatistics() {
        return statistics;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractStatisticValue<?> that = (AbstractStatisticValue<?>) o;
        return new EqualsBuilder().append(statistics, that.statistics).append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(statistics).append(value).toHashCode();
    }
}
