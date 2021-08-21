package com.luckyzz.sporegame.sidebar;

import com.akamecoder.cristalix.sidebar.*;
import com.akamecoder.cristalix.sidebar.updater.PerSecondSidebarLineUpdater;
import com.akamecoder.cristalix.util.key.PlayerUniqueKey;
import com.luckyzz.sporegame.character.exception.CharacterException;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.statistic.Statistics;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ArenaSporeSidebar extends AbstractSporeSidebar {

    ArenaSporeSidebar(@NotNull final SidebarService sidebarService) {
        super(sidebarService, user -> user.getCharacterSession().isPresent());
    }

    @Override
    public @NotNull Function<SporeUser, SidebarIndividual> getCreationFunction() {
        return user -> new ArenaSidebar(sidebarService, user);
    }

    private static class ArenaSidebar extends IndividualBukkitSidebar {

        private final SporeUser user;

        private ArenaSidebar(@NotNull final SidebarService service, @NotNull final SporeUser user) {
            super(PlayerUniqueKey.wrap(user.getPlayer()), service);
            this.user = user;
            this.showFor(user.getPlayer());
        }

        private @NotNull String calculateDirection(@NotNull final SporeCharacterSession session) {
            return session.getDirection().getColoredName();
        }

        private @NotNull String calculateLevel(@NotNull final SporeCharacterSession session) {
            return session.getDetailedLevel().getRomanIndex();
        }

        private @NotNull String calculateKills() {
            return String.valueOf(user.getStatistic().getStatistic(Statistics.KILLS).intValue());
        }

        @Override
        protected void generate(@NotNull final SidebarGeneration sidebar) {
            final SporeCharacterSession session = user.getCharacterSession().orElseThrow(() -> {
                return new CharacterException("Session has no exists!");
            });

            sidebar.objectiveName("sporesidebar").displayName(ChatColor.AQUA + "SPORE");
            sidebar.line(8, "");
            sidebar.line(7, ChatColor.WHITE + "Персонаж");
            sidebar.line(6, ChatColor.GREEN + calculateDirection(session));
            sidebar.line(5, "");
            sidebar.line(4, ChatColor.WHITE + "Уровень");
            sidebar.line(3, ChatColor.GOLD + "Подсчет...", new PerSecondSidebarLineUpdater() {
                @Override
                public void doUpdate(@NotNull final Sidebar sidebar, @NotNull final SidebarLine line) {
                    line.setText(ChatColor.GOLD + calculateLevel(session));
                }
            });
            sidebar.line(2, "");
            sidebar.line(1, ChatColor.WHITE + "Убийств");
            sidebar.line(0, ChatColor.GOLD + "Подсчет...", new PerSecondSidebarLineUpdater() {
                @Override
                public void doUpdate(@NotNull final Sidebar sidebar, @NotNull final SidebarLine line) {
                    line.setText(ChatColor.RED + calculateKills());
                }
            });
        }

    }

}
