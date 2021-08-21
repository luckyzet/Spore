package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.character.AbstractSporeCharacter;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.inventory.CharacterInventory;
import com.luckyzz.sporegame.character.item.CharacterItemStorage;
import com.luckyzz.sporegame.character.level.CharacterLevel;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

class DetailedSporeCharacterImpl extends AbstractSporeCharacter implements DetailedSporeCharacter {

    private Location location;
    private CharacterLevel level;
    private final CharacterSpecifications specifications;
    private final CharacterItemStorage itemStorage;
    private final CharacterInventory inventory;

    DetailedSporeCharacterImpl(@NotNull final SporeUserRepository.CharacterRepository repository, @NotNull final SporeUser user,
                               @NotNull final String name, @NotNull final CharacterDirection direction,
                               @NotNull final Location location, @NotNull final CharacterSpecifications specifications,
                               @NotNull final Function<DetailedSporeCharacter, CharacterLevel> level, @NotNull final CharacterItemStorage itemStorage,
                               @NotNull final CharacterInventory inventory) {
        super(repository, user, name, direction);
        this.location = location;
        this.specifications = specifications;
        this.level = level.apply(this);
        this.itemStorage = itemStorage;
        this.inventory = inventory;
    }

    @Override
    public @NotNull Location getLocation() {
        return location;
    }

    @Override
    public @NotNull CharacterSpecifications getSpecifications() {
        return specifications;
    }

    @Override
    public @NotNull CharacterLevel getDetailedLevel() {
        return level;
    }

    @Override
    public @NotNull CharacterInventory getInventory() {
        return inventory;
    }

    @Override
    public @NotNull CharacterItemStorage getItemStorage() {
        return itemStorage;
    }

    @Override
    public void setLocation(@NotNull final Location location) {
        this.location = location;
    }

    @Override
    public void setDetailedLevel(@NotNull final CharacterLevel level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level.getIndex();
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DetailedSporeCharacterImpl that = (DetailedSporeCharacterImpl) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(location, that.location)
                .append(specifications, that.specifications)
                .append(level, that.level)
                .append(itemStorage, that.itemStorage)
                .append(inventory, that.inventory)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(location)
                .append(specifications)
                .append(level)
                .append(itemStorage)
                .append(inventory)
                .toHashCode();
    }
}
