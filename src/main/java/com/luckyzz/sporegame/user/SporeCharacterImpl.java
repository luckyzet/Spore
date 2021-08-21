package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.character.AbstractSporeCharacter;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

class SporeCharacterImpl extends AbstractSporeCharacter {

    private final int level;

    SporeCharacterImpl(@NotNull final SporeUserRepository.CharacterRepository repository, @NotNull final SporeUser user,
                       @NotNull final String name, @NotNull final CharacterDirection direction, final int level) {
        super(repository, user, name, direction);
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public @NotNull CompletableFuture<DetailedSporeCharacter> loadDetailed() {
        return repository.loadDetailed(user, this);
    }

}
