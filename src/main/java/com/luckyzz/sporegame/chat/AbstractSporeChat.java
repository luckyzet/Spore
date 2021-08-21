package com.luckyzz.sporegame.chat;

import com.luckyzz.sporegame.user.SporeUser;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public abstract class AbstractSporeChat implements SporeChat {

    protected final Predicate<SporeUser> predicate;

    protected AbstractSporeChat(@NotNull final Predicate<SporeUser> predicate) {
        this.predicate = predicate;
    }

    @Override
    public @NotNull Predicate<SporeUser> getPredicate() {
        return predicate;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractSporeChat that = (AbstractSporeChat) o;
        return new EqualsBuilder().append(predicate, that.predicate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(predicate).toHashCode();
    }
}
