package com.luckyzz.sporegame.select;

import com.akamecoder.cristalix.chat.click.ChatClickSessionService;
import com.akamecoder.cristalix.chat.input.ChatInputService;
import com.luckyzz.sporegame.select.name.CharacterNameMatcher;
import com.luckyzz.sporegame.user.SporeUserRepository;
import com.luckyzz.sporegame.user.SporeUserService;
import com.luckyzz.sporegame.character.SporeCharacterFactory;
import com.luckyzz.sporegame.util.invulnerability.InvulnerabilityService;
import com.luckyzz.sporegame.character.session.CharacterSessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.invoice.IInvoiceService;

public final class SelectionCharacterManagement {

    private final SelectCharacterProcessor processor;

    public SelectionCharacterManagement(@NotNull final SporeUserService userService, @NotNull final SporeUserRepository.CharacterRepository repository,
                                        @NotNull final IInvoiceService invoiceService, @NotNull final ChatInputService inputService,
                                        @NotNull final ChatClickSessionService clickService, @NotNull final CharacterSessionFactory sessionFactory,
                                        @NotNull final SporeCharacterFactory characterFactory, @NotNull final InvulnerabilityService invulnerabilityService) {
        final CharacterNameMatcher nameMatcher = new CompleteCharacterNameMatcher(repository);
        processor = new MenuSelectCharacterProcessor(repository, invoiceService, inputService,
                clickService, nameMatcher, sessionFactory, characterFactory, invulnerabilityService);

        new SelectCharacterCommand(userService, processor);
    }

    public @NotNull SelectCharacterProcessor getProcessor() {
        return processor;
    }

}
