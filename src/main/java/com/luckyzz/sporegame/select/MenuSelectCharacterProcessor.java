package com.luckyzz.sporegame.select;

import com.akamecoder.cristalix.chat.click.ChatClickSessionService;
import com.akamecoder.cristalix.chat.input.ChatInputService;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserRepository;
import com.luckyzz.sporegame.character.SporeCharacterFactory;
import com.luckyzz.sporegame.util.invulnerability.InvulnerabilityService;
import com.luckyzz.sporegame.select.name.CharacterNameMatcher;
import com.luckyzz.sporegame.character.session.CharacterSessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.invoice.IInvoiceService;

class MenuSelectCharacterProcessor implements SelectCharacterProcessor {

    private final SporeUserRepository.CharacterRepository repository;
    private final IInvoiceService invoiceService;
    private final ChatInputService inputService;
    private final ChatClickSessionService clickService;
    private final CharacterNameMatcher nameMatcher;
    private final CharacterSessionFactory sessionFactory;
    private final SporeCharacterFactory characterFactory;
    private final InvulnerabilityService invulnerabilityService;

    MenuSelectCharacterProcessor(@NotNull final SporeUserRepository.CharacterRepository repository, @NotNull final IInvoiceService invoiceService,
                                 @NotNull final ChatInputService inputService, @NotNull final ChatClickSessionService clickService,
                                 @NotNull final CharacterNameMatcher nameMatcher, @NotNull final CharacterSessionFactory sessionFactory,
                                 @NotNull final SporeCharacterFactory characterFactory, @NotNull final InvulnerabilityService invulnerabilityService) {
        this.repository = repository;
        this.invoiceService = invoiceService;
        this.inputService = inputService;
        this.clickService = clickService;
        this.nameMatcher = nameMatcher;
        this.sessionFactory = sessionFactory;
        this.characterFactory = characterFactory;
        this.invulnerabilityService = invulnerabilityService;
    }

    @Override
    public @NotNull SelectCharacterMenu selectFor(@NotNull final SporeUser user) {
        return new SelectCharacterMenu(repository, invoiceService, user, inputService, clickService, nameMatcher, sessionFactory, characterFactory, invulnerabilityService);
    }

}
