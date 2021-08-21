package com.luckyzz.sporegame.sidebar;

import com.akamecoder.cristalix.sidebar.IndividualBukkitSidebar;
import com.akamecoder.cristalix.sidebar.SidebarGeneration;
import com.akamecoder.cristalix.sidebar.SidebarIndividual;
import com.akamecoder.cristalix.sidebar.SidebarService;
import com.akamecoder.cristalix.util.key.PlayerUniqueKey;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.SporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.statistic.Statistics;
import com.luckyzz.sporegame.util.RomanNumeral;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class SpawnSporeSidebar extends AbstractSporeSidebar {

    SpawnSporeSidebar(@NotNull final SidebarService sidebarService) {
        super(sidebarService, user -> !user.getCharacterSession().isPresent());
    }

    @Override
    public @NotNull Function<SporeUser, SidebarIndividual> getCreationFunction() {
        return user -> new SpawnSidebar(sidebarService, user);
    }

    private static class SpawnSidebar extends IndividualBukkitSidebar {

        private final SporeUser user;

        private SpawnSidebar(@NotNull final SidebarService service, @NotNull final SporeUser user) {
            super(PlayerUniqueKey.wrap(user.getPlayer()), service);
            this.user = user;
            this.showFor(user.getPlayer());
        }

        private @NotNull String calculateDirection() {
            final SporeCharacters characters = user.getCharacters();
            final int count = characters.getCharacterCount();
            if(count == 0) {
                return "Неопределенно";
            }

            int countHerbivores = (int) characters.getCharacters().stream().filter(character -> character.getDirection() == CharacterDirection.HERBIVORES).count();

            if(countHerbivores >= (count / 2)) {
                return CharacterDirection.HERBIVORES.getName();
            }

            return CharacterDirection.CARNIVORES.getName();
        }

        private @NotNull String calculateLevel() {
            final SporeCharacters characters = user.getCharacters();
            final int level = characters.getCharacters().stream().mapToInt(SporeCharacter::getLevel).sum();
            if(level == 0) {
                return "Неопределенно";
            }

            return RomanNumeral.toRoman(level);
        }

        private @NotNull String calculateKills() {
            return String.valueOf(user.getStatistic().getStatistic(Statistics.KILLS).intValue());
        }

        @Override
        protected void generate(@NotNull final SidebarGeneration sidebar) {
            sidebar.objectiveName("sporesidebar").displayName(ChatColor.AQUA + "SPORE");
            sidebar.line(7, ChatColor.WHITE + "Персонаж");
            sidebar.line(6, ChatColor.GREEN + calculateDirection());
            sidebar.line(5, "");
            sidebar.line(4, ChatColor.WHITE + "Уровень");
            sidebar.line(3, ChatColor.GOLD + calculateLevel());
            sidebar.line(2, "");
            sidebar.line(1, ChatColor.WHITE + "Убийств");
            sidebar.line(0, ChatColor.RED + calculateKills());
        }

    }

}
