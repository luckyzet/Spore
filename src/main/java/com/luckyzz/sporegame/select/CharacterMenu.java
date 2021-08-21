package com.luckyzz.sporegame.select;

import com.akamecoder.cristalix.chat.click.ChatClickSessionService;
import com.akamecoder.cristalix.chat.input.ChatInputService;
import com.akamecoder.cristalix.menu.SingleMenuSession;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserRepository;
import com.luckyzz.sporegame.character.SporeCharacterFactory;
import com.luckyzz.sporegame.util.invulnerability.InvulnerabilityService;
import com.luckyzz.sporegame.select.name.CharacterNameMatcher;
import com.luckyzz.sporegame.character.session.CharacterSessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.invoice.IInvoiceService;

abstract class CharacterMenu extends SingleMenuSession {

    final SporeUserRepository.CharacterRepository repository;
    final IInvoiceService invoiceService;
    final SporeUser user;
    final ChatInputService inputService;
    final ChatClickSessionService clickService;
    final CharacterNameMatcher nameMatcher;
    final CharacterSessionFactory sessionFactory;
    final SporeCharacterFactory characterFactory;
    final InvulnerabilityService invulnerabilityService;

    CharacterMenu(@NotNull final SporeUserRepository.CharacterRepository repository, @NotNull final IInvoiceService invoiceService, @NotNull final SporeUser user,
                  @NotNull final ChatInputService inputService, @NotNull final ChatClickSessionService clickService,
                  @NotNull final CharacterNameMatcher nameMatcher, @NotNull final CharacterSessionFactory sessionFactory,
                  @NotNull final SporeCharacterFactory characterFactory, @NotNull final InvulnerabilityService invulnerabilityService) {
        this.repository = repository;
        this.invoiceService = invoiceService;
        this.user = user;
        this.inputService = inputService;
        this.clickService = clickService;
        this.nameMatcher = nameMatcher;
        this.sessionFactory = sessionFactory;
        this.characterFactory = characterFactory;
        this.invulnerabilityService = invulnerabilityService;
    }

    CharacterMenu(@NotNull final CharacterMenu menu) {
        this(menu.repository, menu.invoiceService, menu.user, menu.inputService, menu.clickService,
                menu.nameMatcher, menu.sessionFactory, menu.characterFactory, menu.invulnerabilityService);
    }

}
