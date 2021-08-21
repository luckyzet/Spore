package com.luckyzz.sporegame.config;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.level.AbstractCharacterLevel;
import com.luckyzz.sporegame.character.level.DNA;
import com.luckyzz.sporegame.user.event.SporeUserLevelUpEvent;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProgressingCharacterLevel extends AbstractCharacterLevel {

    private final DetailedSporeCharacter character;
    private final ConfigurationCharacterLevel next;
    private final DNA progress;

    ProgressingCharacterLevel(@NotNull final DetailedSporeCharacter character, @NotNull final ConfigurationCharacterLevel level, final double progress) {
        super(level.getIndex(), level.getCheckpoints());
        this.character = character;
        this.progress = new DNA(progress);
        this.next = level.getNextLevel().orElse(null);
    }

    @Override
    public @NotNull DNA getCurrentProgress() {
        return progress.clone();
    }

    @Override
    public boolean changeCurrentProgress(final double delta) {
        progress.plusValue(delta);

        if(getProgressLeft().getValue() > 0) {
            return false;
        }

        getNextLevel().ifPresent(next -> {
            final SporeUserLevelUpEvent event = new SporeUserLevelUpEvent(character.getUser());
            Bukkit.getPluginManager().callEvent(event);
            character.setDetailedLevel(next.toProgressing(character, -getProgressLeft().getValue()));
        });

        return true;
    }

    @Override
    public @NotNull AkameOptional<ConfigurationCharacterLevel> getNextLevel() {
        return AkameOptional.ofNullable(next);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProgressingCharacterLevel that = (ProgressingCharacterLevel) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(character, that.character)
                .append(progress, that.progress)
                .append(next, that.next)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(character)
                .append(progress)
                .append(next)
                .toHashCode();
    }
}
