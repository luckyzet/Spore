package com.luckyzz.sporegame.tab;

import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.tab.exception.TabException;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserService;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.tab.ITabTextFormatter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;

class SporeTabTextFormatter implements ITabTextFormatter {

    private final SporeUserService userService;

    private final Set<FormattingStrategy> strategies = new HashSet<>();

    SporeTabTextFormatter(@NotNull final SporeUserService userService) {
        this.userService = userService;

        strategies.add(new SpawnFormattingStrategy());
        strategies.add(new ArenaFormattingStrategy());
    }

    private abstract static class FormattingStrategy {

        protected final Predicate<SporeUser> predicate;

        protected FormattingStrategy(@NotNull final Predicate<SporeUser> predicate) {
            this.predicate = predicate;
        }

        public @NotNull Predicate<SporeUser> getPredicate() {
            return predicate;
        }

        public abstract @NotNull String format(@NotNull final SporeUser user);

    }

    private static class SpawnFormattingStrategy extends FormattingStrategy {

        SpawnFormattingStrategy() {
            super(user -> !user.getCharacterSession().isPresent());
        }

        @Override
        public @NotNull String format(@NotNull final SporeUser user) {
            return user.getName();
        }

    }

    private static class ArenaFormattingStrategy extends FormattingStrategy {

        ArenaFormattingStrategy() {
            super(user -> user.getCharacterSession().isPresent());
        }

        @Override
        public @NotNull String format(@NotNull final SporeUser user) {
            final SporeCharacterSession session = user.getCharacterSession().orElseThrow(() -> {
                return new TabException("Session has not exists!");
            });
            return session.getDirection().getColor() + "[" + session.getName() + "] " + user.getName() + " " + session.getDetailedLevel().getRomanIndex();
        }

    }

    @Override
    public @NotNull CompletableFuture<BaseComponent[]> format(@NotNull final UUID uuid) {
        final SporeUser user = userService.getUser(Bukkit.getPlayer(uuid));
        final Stream<FormattingStrategy> strategyStream = strategies.stream()
                .filter(got -> got.getPredicate().test(user));
        final FormattingStrategy strategy = strategyStream.findFirst().orElseThrow(() -> {
            return new TabException("For user " + user.getName() + " was found no one strategy!");
        });
        return CompletableFuture.completedFuture(new ComponentBuilder(new TextComponent(strategy.format(user))).create());
    }

}
