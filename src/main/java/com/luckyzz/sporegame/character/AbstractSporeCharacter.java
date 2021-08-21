package com.luckyzz.sporegame.character;

import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserRepository;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSporeCharacter implements SporeCharacter {

    protected final SporeUserRepository.CharacterRepository repository;

    protected final SporeUser user;
    protected final String name;
    protected final CharacterDirection direction;

    protected AbstractSporeCharacter(@NotNull final SporeUserRepository.CharacterRepository repository, @NotNull final SporeUser user,
                                     @NotNull final String name, @NotNull final CharacterDirection direction) {
        this.repository = repository;
        this.user = user;
        this.name = name;
        this.direction = direction;
    }

    @Override
    public @NotNull SporeUser getUser() {
        return user;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull CharacterDirection getDirection() {
        return direction;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractSporeCharacter that = (AbstractSporeCharacter) o;
        return new EqualsBuilder()
                .append(repository, that.repository)
                .append(user, that.user)
                .append(name, that.name)
                .append(direction, that.direction)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(repository)
                .append(user)
                .append(name)
                .append(direction)
                .toHashCode();
    }
}
