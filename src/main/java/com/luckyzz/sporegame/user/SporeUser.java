package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.util.AkameOptional;
import com.akamecoder.cristalix.util.player.PlayerFilters;
import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.statistic.SporeUserStatistic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface SporeUser {

    default boolean isOnline() {
        return getPlayerOptional().isPresent();
    }

    @NotNull SporeWorld getWorld();

    void setWorld(@NotNull final SporeWorld world);

    @NotNull UUID getUniqueId();

    @NotNull String getName();

    default @NotNull AkameOptional<Player> getPlayerOptional() {
        return PlayerFilters.getPlayer(getName());
    }

    default @NotNull Player getPlayer() {
        return PlayerFilters.getPlayer(getName()).get();
    }

    @NotNull SporeCharacters getCharacters();

    @NotNull SporeUserStatistic getStatistic();

    @NotNull AkameOptional<SporeCharacterSession> getCharacterSession();

    void setCharacterSession(@Nullable final SporeCharacterSession characterSession);

}
