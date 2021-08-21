package com.luckyzz.sporegame.character;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Pre loaded version of spore character
 */
public interface SporeCharacter {

    @NotNull SporeUser getUser();

    @NotNull String getName();

    @NotNull CharacterDirection getDirection();

    int getLevel();

    @NotNull CompletableFuture<DetailedSporeCharacter> loadDetailed();

}
