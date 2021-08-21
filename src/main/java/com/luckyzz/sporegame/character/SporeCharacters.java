package com.luckyzz.sporegame.character;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.user.SporeUser;
import com.luckyzz.sporegame.user.SporeUserRepository;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface SporeCharacters {

    @NotNull SporeUser getUser();

    default int getMaximumServerCharacterCount() {
        return 27;
    }

    int getMaximumCharacterCount();

    int incrementMaximumCharacterCount(@NotNull final SporeUserRepository.CharacterRepository repository);

    int getCharacterCount();

    @NotNull AkameOptional<SporeCharacter> getCharacter(final int index);

    default @NotNull AkameOptional<SporeCharacterSession> getSession() {
        return getUser().getCharacterSession();
    }

    @NotNull Collection<SporeCharacter> getCharacters();

    void addCharacter(@NotNull final SporeCharacter character);

}
