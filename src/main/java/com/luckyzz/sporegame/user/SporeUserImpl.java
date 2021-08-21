package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.statistic.SporeUserStatistic;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Function;

class SporeUserImpl implements SporeUser {

    private final UUID uuid;
    private final String name;
    private final SporeCharacters characters;
    private final SporeUserStatistic statistic;
    private SporeWorld world;
    private SporeCharacterSession characterSession;

    SporeUserImpl(@NotNull final SporeWorld world, @NotNull final UUID uuid, @NotNull final String name,
                  @NotNull final Function<SporeUser, SporeCharacters> characters, @NotNull final Function<SporeUser, SporeUserStatistic> statistic) {
        this.world = world;
        this.uuid = uuid;
        this.name = name;
        this.characters = characters.apply(this);
        this.statistic = statistic.apply(this);
    }

    @Override
    public @NotNull SporeWorld getWorld() {
        return world;
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return uuid;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull SporeCharacters getCharacters() {
        return characters;
    }

    @Override
    public @NotNull SporeUserStatistic getStatistic() {
        return statistic;
    }

    @Override
    public @NotNull AkameOptional<SporeCharacterSession> getCharacterSession() {
        return AkameOptional.ofNullable(characterSession);
    }

    @Override
    public void setCharacterSession(@Nullable final SporeCharacterSession characterSession) {
        this.characterSession = characterSession;
    }

    @Override
    public void setWorld(@NotNull final SporeWorld world) {
        this.world = world;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SporeUserImpl sporeUser = (SporeUserImpl) o;
        return new EqualsBuilder().append(name, sporeUser.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).toHashCode();
    }
}
