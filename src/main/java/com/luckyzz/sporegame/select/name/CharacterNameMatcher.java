package com.luckyzz.sporegame.select.name;

import org.jetbrains.annotations.NotNull;

public interface CharacterNameMatcher {

    enum MatchResult {

        FAILED_LENGTH(false),
        FAILED_SYMBOLS(false),
        FAILED_SPACE(false),
        FAILED_USED(false),
        SUCCESS(true);

        private final boolean result;

        MatchResult(final boolean result) {
            this.result = result;
        }

        public boolean getResult() {
            return result;
        }

    }

    @NotNull MatchResult match(@NotNull final String name);

}
