package com.luckyzz.sporegame.select;

import com.akamecoder.cristalix.chat.input.ChatInputSession;
import com.akamecoder.cristalix.component.builder.TextComponentBuilder;
import com.akamecoder.cristalix.menu.MenuClicks;
import com.akamecoder.cristalix.menu.MenuGeneration;
import com.akamecoder.cristalix.menu.icon.MenuIconClickable;
import com.akamecoder.cristalix.util.inventory.InventorySize;
import com.akamecoder.cristalix.util.itemstack.builder.ItemStackBuilders;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.DetailedSporeCharacter;
import com.luckyzz.sporegame.util.invulnerability.InvulnerabilityOption;
import com.luckyzz.sporegame.select.name.CharacterNameMatcher;
import com.luckyzz.sporegame.util.constant.SporeMenu;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiPredicate;

public class CreationCharacterMenu extends CharacterMenu {

    private static final Multimap<Player, UUID> SESSIONS = HashMultimap.create();

    CreationCharacterMenu(@NotNull final CharacterMenu menu) {
        super(menu);
        openFor(user.getPlayer());
    }

    private @NotNull UUID generateRandom() {
        UUID uuid = UUID.randomUUID();
        while (SESSIONS.containsValue(uuid)) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }

    private @NotNull MenuIconClickable chatNameClickable(@NotNull final CharacterDirection direction) {
        return MenuClicks.cancel().andThen(click -> {
            if(SESSIONS.containsKey(click.getPlayer())) {
                SESSIONS.forEach((pl, uuid) -> clickService.removeSession(uuid));
            }

            click.getSession().close();
            SporeMessages.CREATION_CHARACTER_NAME_TYPE.send(click.getPlayer());
            inputService.createSession(ChatInputSession.newBuilder().player(click.getPlayer()).cancelEvent(true).saveAfterReconnect(false).predicate(predicateChatName()).action((player, message1) -> {
                final UUID yesUuid = generateRandom();
                final UUID noUuid = generateRandom();

                final String message = message1.trim();
                final TextComponent component = TextComponentBuilder.newBuilder(clickService)
                        .text(SporeMessages.PREFIX + "Вы собираетесь создать персонажа [" + direction.getColor() + message + ChatColor.WHITE + "]. Направление: " + direction.getColoredName() + "\n")
                        .extra(TextComponentBuilder.newBuilder(clickService)
                                .text(SporeMessages.PREFIX + "Нажмите ДА, если все верно!\n")
                                .onClick(yesUuid, player1 -> {
                                    final DetailedSporeCharacter character = characterFactory.createCharacter(user, direction, message);
                                    sessionFactory.createSession(character, session -> {
                                        invulnerabilityService.createInvulnerability(3, player1) // TODO 20
                                                .option(InvulnerabilityOption.INVISIBLE)
                                                .option(InvulnerabilityOption.BE_DAMAGED)
                                                .option(InvulnerabilityOption.DAMAGE_ANOTHER);
                                        SporeMessages.WELCOME_SPORE_INVULNERABILITY.send(player);
                                    });
                                }))
                        .extra(TextComponentBuilder.newBuilder(clickService)
                                .text(SporeMessages.PREFIX + "Нажмите НЕТ, если есть ошибка!")
                                .onClick(noUuid, player1 -> new SelectCharacterMenu(this))
                        ).create();
                player.sendMessage(component);
                SESSIONS.put(player, yesUuid);
                SESSIONS.put(player, noUuid);
            }));
        });
    }

    private @NotNull BiPredicate<Player, String> predicateChatName() {
        return (player, message) -> {
            final CharacterNameMatcher.MatchResult result = nameMatcher.match(message);

            if(result == CharacterNameMatcher.MatchResult.FAILED_LENGTH) {
                SporeMessages.CREATION_CHARACTER_NAME_LENGTH.toAdaptive().placeholder("%count%", message.length() - 8)
                        .send(player);
                return false;
            }
            if(result == CharacterNameMatcher.MatchResult.FAILED_SYMBOLS) {
                SporeMessages.CREATION_CHARACTER_NAME_SYMBOLS.send(player);
                return false;
            }
            if(result == CharacterNameMatcher.MatchResult.FAILED_SPACE) {
                SporeMessages.CREATION_CHARACTER_NAME_SPACE.send(player);
                return false;
            }
            if(result == CharacterNameMatcher.MatchResult.FAILED_USED) {
                SporeMessages.CREATION_CHARACTER_NAME_USED.send(player);
                return false;
            }
            return true;
        };
    }

    @Override
    protected void getOpenAction(@NotNull final Player player) {
        SporeMessages.CREATION_CHARACTER_MENU_OPEN.send(player);
    }

    @Override
    protected void generateMenu(@NotNull final MenuGeneration menu) {
        menu.metadata().size(InventorySize.SIX_ROWS).title(SporeMessages.MENU_PREFIX + "Выбери направление").back().icons(icons -> {
            icons.icon(SporeMenu.PANE.display(ChatColor.GRAY + "Выбери направление").create(), MenuClicks.cancel(), SporeMenu.SIDE_PANES);

            final ItemStack backItem = ItemStackBuilders.newBuilder()
                    .type(Material.CLAY_BALL)
                    .nbt("other", "arrow_left")
                    .display(ChatColor.GOLD + "Вернуться обратно")
                    .create();
            icons.icon(37, backItem, MenuClicks.cancel().andThen(click -> new SelectCharacterMenu(this)));

            final ItemStack herbivorousItem = ItemStackBuilders.newBuilder()
                    .type(Material.CLAY_BALL)
                    .nbt("spore", "herbivorous")
                    .display(ChatColor.GOLD + "Травоядные")
                    .lore(ChatColor.AQUA + "Начни играть за травоядных, твое главное оружие - это твоя обворожительность!")
                    .lore(ChatColor.AQUA + "Дружи со всеми, кушай то, что выросло из земли и люби этот мир!")
                    .lore("")
                    .lore(ChatColor.AQUA + "Начальные характеристики:")
                    .lore(ChatColor.AQUA + " - Скорость: " + ChatColor.GREEN + "0.14х")
                    .lore(ChatColor.AQUA + " - ХП: "  + ChatColor.GREEN + "4 сердца")
                    .lore(ChatColor.AQUA + " - Урон: "  + ChatColor.GREEN + "1 ХП")
                    .create();
            icons.icon(20, herbivorousItem, chatNameClickable(CharacterDirection.HERBIVORES));

            final ItemStack carnivorousItem = ItemStackBuilders.newBuilder()
                    .type(Material.CLAY_BALL)
                    .nbt("spore", "carnivorous")
                    .display(ChatColor.GOLD + "Плотоядные")
                    .lore(ChatColor.AQUA + "Твои клыки и когти - твое главное оружие.")
                    .lore(ChatColor.AQUA + "Ищи жертву, которая слабее тебя - и убивай её.")
                    .lore(ChatColor.AQUA + "А рыбку покрупнее, можно завалить толпой.")
                    .lore("")
                    .lore(ChatColor.AQUA + "Начальные характеристики:")
                    .lore(ChatColor.AQUA + " - Скорость: " + ChatColor.GREEN + "0.10х")
                    .lore(ChatColor.AQUA + " - ХП: " + ChatColor.GREEN + "4 сердца")
                    .lore(ChatColor.AQUA + " - Урон: " + ChatColor.GREEN + "1.5 ХП")
                    .create();
            icons.icon(24, carnivorousItem, chatNameClickable(CharacterDirection.CARNIVORES));
        });
    }

}
