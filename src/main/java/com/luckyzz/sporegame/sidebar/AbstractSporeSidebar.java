package com.luckyzz.sporegame.sidebar;

import com.akamecoder.cristalix.sidebar.SidebarService;
import com.luckyzz.sporegame.user.SporeUser;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public abstract class AbstractSporeSidebar implements SporeSidebar {

    protected final SidebarService sidebarService;
    protected final Predicate<SporeUser> predicate;

    protected AbstractSporeSidebar(@NotNull final SidebarService sidebarService, @NotNull final Predicate<SporeUser> predicate) {
        this.sidebarService = sidebarService;
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
        final AbstractSporeSidebar that = (AbstractSporeSidebar) o;
        return new EqualsBuilder()
                .append(sidebarService, that.sidebarService)
                .append(predicate, that.predicate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sidebarService)
                .append(predicate)
                .toHashCode();
    }
}
