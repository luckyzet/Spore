package com.luckyzz.sporegame.user;

import com.luckyzz.sporegame.world.SporeWorld;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.character.session.CharacterSessionFactory;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.event.SporeUserLoadingSessionEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class PlayingCharacterSessionFactory implements CharacterSessionFactory {

    private final SporeWorld world;
    private final SporeUserRepository.CharacterRepository repository;

    PlayingCharacterSessionFactory(@NotNull final SporeWorld world, @NotNull final SporeUserRepository.CharacterRepository repository) {
        this.world = world;
        this.repository = repository;
    }

    @Override
    public @NotNull SporeCharacterSession createSession(@NotNull final DetailedSporeCharacter character, @NotNull final Consumer<SporeCharacterSession> consumer) {
        final PlayingSporeCharacterSession session = new PlayingSporeCharacterSession(character);

        final SporeUser user = character.getUser();
        final Player player = user.getPlayer();

        user.setCharacterSession(session);
        user.setWorld(world);

        // TODO: Msg success
        player.teleport(session.getLocation());
        session.getInventory().applyInventory();
        character.getSpecifications().applyTo(player);

        final SporeUserLoadingSessionEvent event = new SporeUserLoadingSessionEvent(user);
        Bukkit.getPluginManager().callEvent(event);

        consumer.accept(session);
        return session;
    }

}
