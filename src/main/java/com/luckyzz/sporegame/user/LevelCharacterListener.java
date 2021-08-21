package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.event.handle.EventHandle;
import com.luckyzz.sporegame.character.CharacterLevelUpdateProcessor;
import com.luckyzz.sporegame.user.event.SporeUserLevelUpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

class LevelCharacterListener extends EventHandle {

    private final CharacterLevelUpdateProcessor processor;

    LevelCharacterListener(@NotNull final Plugin plugin, @NotNull final CharacterLevelUpdateProcessor processor) {
        super(plugin);
        this.processor = processor;
    }

    @EventHandler
    public void onLevel(@NotNull final SporeUserLevelUpEvent event) {
        processor.update(event.getSession(), event.getNextLevel());
    }

}
