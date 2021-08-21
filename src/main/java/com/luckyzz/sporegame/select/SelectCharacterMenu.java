package com.luckyzz.sporegame.select;

import com.akamecoder.cristalix.chat.click.ChatClickSessionService;
import com.akamecoder.cristalix.chat.input.ChatInputService;
import com.akamecoder.cristalix.menu.MenuClicks;
import com.akamecoder.cristalix.menu.MenuGeneration;
import com.akamecoder.cristalix.util.inventory.InventorySize;
import com.akamecoder.cristalix.util.itemstack.builder.ItemStackBuilders;
import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.SporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacterFactory;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.character.session.CharacterSessionFactory;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import com.luckyzz.sporegame.util.invulnerability.InvulnerabilityService;
import com.luckyzz.sporegame.select.name.CharacterNameMatcher;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserRepository;
import com.luckyzz.sporegame.util.constant.SporeMenu;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.invoice.IInvoiceService;
import ru.cristalix.core.invoice.Invoice;

public class SelectCharacterMenu extends CharacterMenu {

    private static final int[] CHARACTERS = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43};

    SelectCharacterMenu(@NotNull final SporeUserRepository.CharacterRepository repository, @NotNull final IInvoiceService invoiceService,
                        @NotNull final SporeUser user, @NotNull final ChatInputService inputService,
                        @NotNull final ChatClickSessionService clickService, @NotNull final CharacterNameMatcher nameMatcher,
                        @NotNull final CharacterSessionFactory sessionFactory, @NotNull final SporeCharacterFactory characterFactory,
                        @NotNull final InvulnerabilityService invulnerabilityService) {
        super(repository, invoiceService, user, inputService, clickService, nameMatcher, sessionFactory, characterFactory, invulnerabilityService);
        openFor(user.getPlayer());
    }

    SelectCharacterMenu(@NotNull final CharacterMenu menu) {
        super(menu);
        openFor(user.getPlayer());
    }

    @Override
    protected void getOpenAction(@NotNull final Player player) {
        SporeMessages.SELECT_CHARACTER_MENU_OPEN.send(player);
    }

    @Override
    protected void generateMenu(@NotNull final MenuGeneration menu) {
        menu.metadata().size(InventorySize.SIX_ROWS).title(SporeMessages.MENU_PREFIX + "Выбор персонажа").back().icons(icons -> {
            icons.icon(SporeMenu.PANE.display(ChatColor.GRAY + "Выбор персонажа").create(), MenuClicks.cancel(), SporeMenu.SIDE_PANES);

            final SporeCharacters characters = user.getCharacters();

            int rowIndex = 0;
            int leftCharacters = characters.getMaximumCharacterCount();
            for(int index : CHARACTERS) {
                if(leftCharacters == 0) {
                    icons.icon(index, ItemStackBuilders.newBuilder()
                            .type(Material.CLAY_BALL)
                            .nbt("other", "lock")
                            .display(ChatColor.RED + "Закрыто")
                            .lore(ChatColor.AQUA + "Стоимость: " + ChatColor.GREEN + "50 кристаллов")
                            .create(), MenuClicks.cancel().andThen(click -> {
                        invoiceService.bill(player.getUniqueId(), Invoice.builder().price(50).build()).thenAccept(result -> {
                            if(result.isSuccess()) {
                                characters.incrementMaximumCharacterCount(repository);
                                refresh();
                                SporeMessages.SUCCESS_CHARACTER_BUY.send(player);
                                return;
                            }
                            SporeMessages.FAILED_CHARACTER_BUY.toAdaptive().placeholder("%reason%", result.getErrorMessage()).send(player);
                        });
                    }));
                    continue;
                }
                leftCharacters--;

                final SporeCharacter character = characters.getCharacter(rowIndex).orElse(null);
                if(character == null) {
                    final ItemStack creationItem = ItemStackBuilders.newBuilder()
                            .type(Material.TOTEM)
                            .nbt("other", "3")
                            .display(ChatColor.GOLD + "Создать персонажа")
                            .lore(ChatColor.AQUA + "Начни новое приключение!")
                            .create();
                    icons.icon(index, creationItem, MenuClicks.cancel().andThen(click -> new CreationCharacterMenu(this)));
                    continue;
                }

                final CharacterDirection direction = character.getDirection();

                final ItemStack characterItem = ItemStackBuilders.newBuilder()
                        .type(Material.TOTEM)
                        .nbt("other", "3")
                        .display(ChatColor.GOLD + character.getName())
                        .lore(ChatColor.AQUA + "Направление: " + direction.getColoredName(),
                              ChatColor.AQUA + "Нажмите, чтобы загрузить " + SporeMessages.HIGHLIGHTED_PREFIX + "персонажа" + ChatColor.AQUA + " в мир!")
                        .create();
                icons.icon(index, characterItem, MenuClicks.cancel().andThen(click -> {
                    SporeMessages.LOADING_CHARACTER_WAIT.send(player);
                    character.loadDetailed().thenAccept(detailed -> {
                        final SporeCharacterSession session = sessionFactory.createSession(detailed);
                        SporeMessages.LOADING_CHARACTER_SUCCESS.send(player);
                    });
                }));

                rowIndex++;
            }
        });
    }

}
