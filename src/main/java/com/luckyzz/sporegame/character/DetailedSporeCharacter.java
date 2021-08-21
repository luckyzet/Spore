package com.luckyzz.sporegame.character;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.character.inventory.CharacterInventory;
import com.luckyzz.sporegame.character.item.CharacterItemStorage;
import com.luckyzz.sporegame.character.level.CharacterLevel;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Full loaded spore character with inventory and all details
 */
public interface DetailedSporeCharacter extends SporeCharacter {

    @NotNull Location getLocation();

    void setLocation(@NotNull final Location location);

    @NotNull CharacterSpecifications getSpecifications();

    @NotNull CharacterLevel getDetailedLevel();

    void setDetailedLevel(@NotNull final CharacterLevel level);

    @NotNull CharacterItemStorage getItemStorage();

    @NotNull CharacterInventory getInventory();

    default @NotNull AkameOptional<SporeCharacterSession> getSession() {
        return getUser().getCharacterSession();
    }

    @Override
    default @NotNull CompletableFuture<DetailedSporeCharacter> loadDetailed() {
        throw new UnsupportedOperationException();
    }
}
