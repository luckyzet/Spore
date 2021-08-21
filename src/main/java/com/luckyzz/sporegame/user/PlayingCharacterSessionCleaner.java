package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.exception.CharacterException;
import com.luckyzz.sporegame.character.level.CharacterSpecifications;
import com.luckyzz.sporegame.character.session.CharacterSessionCleaner;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.event.SporeUserUnloadingSessionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayingCharacterSessionCleaner implements CharacterSessionCleaner {

    private final SporeWorld world;
    private final SporeUserRepository.CharacterRepository repository;

    PlayingCharacterSessionCleaner(@NotNull final SporeWorld world, @NotNull final SporeUserRepository.CharacterRepository repository) {
        this.world = world;
        this.repository = repository;
    }

    @Override
    public @NotNull DetailedSporeCharacter clearSession(@NotNull final SporeUser user) {
        final SporeCharacterSession session = user.getCharacterSession().orElseThrow(() -> {
            return new CharacterException("Session has no exists!");
        });
        final Player player = user.getPlayer();

        session.makeInactive();

        player.getInventory().clear();
        CharacterSpecifications.SPAWN_DEFAULT.applyTo(player);

        final SporeUserUnloadingSessionEvent event = new SporeUserUnloadingSessionEvent(user);
        Bukkit.getPluginManager().callEvent(event);

        user.setCharacterSession(null);

        return session;
    }

}
