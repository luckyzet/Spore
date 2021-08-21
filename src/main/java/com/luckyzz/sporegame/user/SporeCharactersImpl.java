package com.luckyzz.sporegame.user;

import com.akamecoder.cristalix.util.AkameOptional;
import com.luckyzz.sporegame.character.SporeCharacter;
import com.luckyzz.sporegame.character.SporeCharacters;
import com.luckyzz.sporegame.character.exception.CharacterException;
import com.luckyzz.sporegame.character.session.SporeCharacterSession;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

class SporeCharactersImpl implements SporeCharacters {

    private final SporeUser user;
    private final SporeCharacterMap characterMap;
    private int maximum;

    SporeCharactersImpl(@NotNull final SporeUser user, final int maximum, @NotNull final SporeCharacterMap characterMap) {
        this.user = user;
        this.maximum = maximum;
        this.characterMap = characterMap;
    }

    @Override
    public @NotNull SporeUser getUser() {
        return user;
    }

    @Override
    public int getMaximumCharacterCount() {
        return maximum;
    }

    @Override
    public int incrementMaximumCharacterCount(@NotNull final SporeUserRepository.CharacterRepository repository) {
        if((maximum + 1) > getMaximumServerCharacterCount()) {
            throw new CharacterException("Maximum character count cannot be more than 27!");
        }
        maximum++;
        repository.saveMaximumCharacters(user);
        return maximum;
    }

    @Override
    public int getCharacterCount() {
        return characterMap.size();
    }

    @Override
    public @NotNull AkameOptional<SporeCharacter> getCharacter(final int index) {
        return characterMap.byIndex(index);
    }

    @Override
    public @NotNull AkameOptional<SporeCharacterSession> getSession() {
        return user.getCharacterSession();
    }

    @Override
    public @NotNull Collection<SporeCharacter> getCharacters() {
        return characterMap.characters();
    }

    @Override
    public void addCharacter(@NotNull final SporeCharacter character) {
        characterMap.put(character);
    }
}
