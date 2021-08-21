package com.luckyzz.sporegame.user.statistic.exception;

import org.jetbrains.annotations.NotNull;

public class StatisticException extends RuntimeException {

    public StatisticException(@NotNull final String message) {
        super(message);
    }

}
