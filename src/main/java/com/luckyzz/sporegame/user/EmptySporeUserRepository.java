package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.world.SporeWorldType;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacter;
import com.luckyzz.sporegame.config.SettingConfig;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class EmptySporeUserRepository implements SporeUserRepository {

    private final SettingConfig config;

    EmptySporeUserRepository(@NotNull final SettingConfig config) {
        this.config = config;
    }

    @Override
    public @NotNull CompletableFuture<SporeUser> loadUser(@NotNull final String name) {
        final SporeUser user = new SporeUserImpl(config.getWorld(SporeWorldType.SPAWN), UUID.randomUUID(), name,
                user1 -> new EmptySporeCharacters(name), user1 -> new EmptySporeUserStatistic());
        return CompletableFuture.completedFuture(user);
    }

    @Override
    public void saveWorld(@NotNull final SporeUser user) {

    }

    @Override
    public @NotNull StatisticRepository statisticRepository() {
        return (user, statistics, value) -> {
        };
    }

    @Override
    public @NotNull CharacterRepository characterRepository() {
        return new CharacterRepository() {
            @Override
            public void saveMaximumCharacters(@NotNull final SporeUser user) {
            }

            @Override
            public @NotNull CompletableFuture<Boolean> checkCharacterName(@NotNull final String name) {
                return CompletableFuture.completedFuture(false);
            }

            @Override
            public @NotNull CompletableFuture<DetailedSporeCharacter> loadDetailed(@NotNull final SporeUser user, @NotNull final SporeCharacter character) {
                return CompletableFuture.completedFuture(null);
            }

            @Override
            public void saveCharacterLocation(@NotNull final DetailedSporeCharacter character) {
            }

            @Override
            public void createCharacter(@NotNull final DetailedSporeCharacter character) {
            }
        };
    }

    @Override
    public void cancel() {
    }

}
