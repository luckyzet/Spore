package com.luckyzz.sporegame.select;

import com.luckyzz.sporegame.user.SporeUserRepository;
import com.luckyzz.sporegame.select.name.CharacterNameMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompleteCharacterNameMatcher implements CharacterNameMatcher {

    private final SporeUserRepository.CharacterRepository characterRepository;
    private final Pattern availableSymbols;

    CompleteCharacterNameMatcher(@NotNull final SporeUserRepository.CharacterRepository characterRepository) {
        this(characterRepository, Pattern.compile("[^a-zA-Zа-яА-Я]"));
    }

    CompleteCharacterNameMatcher(@NotNull final SporeUserRepository.CharacterRepository characterRepository, @NotNull final Pattern availableSymbols) {
        this.availableSymbols = availableSymbols;
        this.characterRepository = characterRepository;
    }

    @Override
    public @NotNull MatchResult match(@NotNull String name) {
        name = name.trim();
        if(name.length() > 8) {
            return MatchResult.FAILED_LENGTH;
        }
        if(name.contains(" ")) {
            return MatchResult.FAILED_SPACE;
        }
        final Matcher matcher = availableSymbols.matcher(name);
        if(matcher.find()) {
            return MatchResult.FAILED_SYMBOLS;
        }
        if(characterRepository.checkCharacterName(name).join()) {
            return MatchResult.FAILED_USED;
        }

        return MatchResult.SUCCESS;
    }

}
