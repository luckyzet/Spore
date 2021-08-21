package com.luckyzz.sporegame.util;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class Log {

    private static final Logger logger = Bukkit.getLogger();

    private Log() {
        throw new UnsupportedOperationException();
    }

    public static void info(@NotNull final String string) {
        logger.info(string);
    }

    public static void warn(@NotNull final String string) {
        logger.warning(string);
    }

}
