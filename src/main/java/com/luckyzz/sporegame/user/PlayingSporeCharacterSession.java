package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.inventory.CharacterInventory;
import com.luckyzz.sporegame.character.item.CharacterItemStorage;
import com.luckyzz.sporegame.character.level.CharacterLevel;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class PlayingSporeCharacterSession implements SporeCharacterSession {

    private boolean active = true;
    private final DetailedSporeCharacter character;

    PlayingSporeCharacterSession(@NotNull final DetailedSporeCharacter character) {
        this.character = character;
    }

    @Override
    public @NotNull Location getLocation() {
        return character.getLocation();
    }

    @Override
    public void setLocation(@NotNull final Location location) {
        character.setLocation(location);
    }

    @Override
    public @NotNull CharacterSpecifications getSpecifications() {
        return character.getSpecifications();
    }

    @Override
    public @NotNull CharacterLevel getDetailedLevel() {
        return character.getDetailedLevel();
    }

    @Override
    public void setDetailedLevel(@NotNull final CharacterLevel level) {
        character.setDetailedLevel(level);
    }

    @Override
    public @NotNull CharacterItemStorage getItemStorage() {
        return character.getItemStorage();
    }

    @Override
    public @NotNull CharacterInventory getInventory() {
        return character.getInventory();
    }

    @Override
    public @NotNull SporeUser getUser() {
        return character.getUser();
    }

    @Override
    public @NotNull String getName() {
        return character.getName();
    }

    @Override
    public @NotNull CharacterDirection getDirection() {
        return character.getDirection();
    }

    @Override
    public int getLevel() {
        return character.getLevel();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void makeInactive() {
        this.active = false;
    }

}
