package com.luckyzz.sporegame.menu;

import com.akamecoder.cristalix.menu.MenuClicks;
import com.akamecoder.cristalix.menu.MenuGeneration;
import com.akamecoder.cristalix.menu.SingleMenuSession;
import com.akamecoder.cristalix.util.inventory.InventorySize;
import com.akamecoder.cristalix.util.itemstack.builder.ItemStackBuilders;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.character.exception.CharacterException;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.user.statistic.SporeUserStatistic;
import com.luckyzz.sporegame.user.statistic.Statistics;
import com.luckyzz.sporegame.util.constant.SporeMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class UserMenu extends SingleMenuSession {

    private final SporeUser user;

    UserMenu(@NotNull final SporeUser user) {
        this.user = user;
        openFor(user.getPlayer());
    }

    @Override
    protected void generateMenu(@NotNull final MenuGeneration menu) {
        final SporeCharacterSession session = user.getCharacterSession().orElseThrow(() -> {
            return new CharacterException("Character session has no exists!");
        });
        final SporeUserStatistic statistic = user.getStatistic();

        menu.metadata().size(InventorySize.SIX_ROWS).title(session.getName()).back().icons(icons -> {
            icons.icon(SporeMenu.PANE.display(session.getName()).create(), MenuClicks.cancel(), SporeMenu.SIDE_PANES);

            final ItemStack statisticItem = ItemStackBuilders.newBuilder()
                    .type(Material.TOTEM)
                    .display(ChatColor.YELLOW + "Статистика")
                    .lore(ChatColor.GOLD + "Убийств: " + statistic.getStatistic(Statistics.KILLS).intValue())
                    .lore(ChatColor.GOLD + "Уровень: " + session.getDetailedLevel().getIndex())
                    .lore(ChatColor.GOLD + "ДНК: " + (int) (session.getDetailedLevel().getCurrentProgress().getValue()))
                    .lore(ChatColor.GOLD + "До следующего уровня нужно: " + (int) (session.getDetailedLevel().getProgressLeft().getValue()) + " ДНК")
                    .lore(ChatColor.GOLD + "Съедено еды: " + statistic.getStatistic(Statistics.FOOD_EATEN).intValue())
                    .create();
            icons.icon(22, statisticItem, MenuClicks.cancel());

            final ItemStack bodyPartsItem = ItemStackBuilders.newBuilder()
                    .type(Material.CLAY_BALL)
                    .display(ChatColor.YELLOW + "Части тела")
                    .lore(ChatColor.GOLD + "Тут вы можете заменить Ваши части тела!")
                    .create();
            icons.icon(20, bodyPartsItem, click -> new BodyPartsUserMenu(user));

            final ItemStack skillsItem = ItemStackBuilders.newBuilder()
                    .type(Material.CLAY_BALL)
                    .display(ChatColor.YELLOW + "Умения")
                    .lore(ChatColor.GOLD + "Тут Вы можете выбрать/убрать умения!")
                    .create();
            icons.icon(24, skillsItem, click -> new SkillsUserMenu(user));
        });
    }

}
