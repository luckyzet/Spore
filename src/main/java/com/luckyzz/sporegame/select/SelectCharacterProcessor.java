package com.luckyzz.sporegame.select;

import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

public interface SelectCharacterProcessor {

    @NotNull SelectCharacterMenu selectFor(@NotNull final SporeUser user);

}
