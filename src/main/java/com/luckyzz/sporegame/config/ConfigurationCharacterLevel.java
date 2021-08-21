package com.luckyzz.sporegame.config;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.level.AbstractCharacterLevel;
import com.luckyzz.sporegame.character.level.DNA;
import org.jetbrains.annotations.NotNull;

public class ConfigurationCharacterLevel extends AbstractCharacterLevel {

    private final SettingConfig config;

    ConfigurationCharacterLevel(@NotNull final SettingConfig config, final int index, @NotNull final LevelCheckpoints checkpoints) {
        super(index, checkpoints);
        this.config = config;
    }

    @Override
    public @NotNull DNA getCurrentProgress() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean changeCurrentProgress(final double delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull AkameOptional<ConfigurationCharacterLevel> getNextLevel() {
        return AkameOptional.ofNullable(config.getLevel(index + 1));
    }

    public @NotNull ProgressingCharacterLevel toProgressing(@NotNull final DetailedSporeCharacter character, final double progress) {
        return new ProgressingCharacterLevel(character, this, progress);
    }

    public @NotNull ProgressingCharacterLevel toProgressing(@NotNull final DetailedSporeCharacter character) {
        return toProgressing(character, 0);
    }

}
