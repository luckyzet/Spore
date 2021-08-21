package com.luckyzz.sporegame.character.session;

import com.luckyzz.sporegame.character.DetailedSporeCharacter;

/**
 * Active character
 * Character with which player is playing now
 */
public interface SporeCharacterSession extends DetailedSporeCharacter {

    boolean isActive();

    void makeInactive();

}
