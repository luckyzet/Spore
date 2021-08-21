package com.luckyzz.sporegame.character;

import com.akamecoder.cristalix.skin.Skin;
import com.akamecoder.cristalix.util.Enums;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import com.luckyzz.sporegame.util.constant.SporeSkins;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public enum CharacterDirection {

    HERBIVORES("Травоядные", ChatColor.GREEN, SporeSkins.HERBIVORES_SKIN, new CharacterSpecifications(0.1, 0.14, 4, 1)),
    CARNIVORES("Плотоядные", ChatColor.RED, SporeSkins.CARNIVORES_SKIN, new CharacterSpecifications(0.1, 0.10, 4, 1.5));

    private final String name;
    private final ChatColor color;
    private final Skin skin;
    private final CharacterSpecifications specifications;

    CharacterDirection(@NotNull final String name, @NotNull final ChatColor color, @NotNull final Skin skin, @NotNull final CharacterSpecifications specifications) {
        this.name = name;
        this.color = color;
        this.skin = skin;
        this.specifications = specifications;
    }

    public @NotNull String getColoredName() {
        return color + name;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull ChatColor getColor() {
        return color;
    }

    public @NotNull Skin getSkin() {
        return skin;
    }

    public @NotNull CharacterSpecifications getSpecifications() {
        return specifications.clone();
    }

    public static @NotNull CharacterDirection fromString(@NotNull final String string) {
        return Enums.of(values(), string);
    }

}
