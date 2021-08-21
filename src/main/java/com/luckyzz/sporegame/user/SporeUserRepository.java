package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.api.Cancelable;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacter;
import com.luckyzz.sporegame.user.statistic.StatisticValue;
import com.luckyzz.sporegame.user.statistic.Statistics;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface SporeUserRepository extends Cancelable {

    @NotNull CompletableFuture<SporeUser> loadUser(@NotNull final String name);

    void saveWorld(@NotNull final SporeUser user);

    @NotNull StatisticRepository statisticRepository();

    interface StatisticRepository {

        void saveStatistic(@NotNull final SporeUser user, @NotNull final Statistics statistics, @NotNull final StatisticValue value);

    }

    @NotNull CharacterRepository characterRepository();

    interface CharacterRepository {

        void saveMaximumCharacters(@NotNull final SporeUser user);

        /**
         * @return true if character name is matches with anyone registered
         */
        @NotNull CompletableFuture<Boolean> checkCharacterName(@NotNull final String name);

        @NotNull CompletableFuture<DetailedSporeCharacter> loadDetailed(@NotNull final SporeUser user, @NotNull final SporeCharacter character);

        void saveCharacterLocation(@NotNull final DetailedSporeCharacter character);

        void createCharacter(@NotNull final DetailedSporeCharacter character);

    }

}
