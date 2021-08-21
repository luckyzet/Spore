package com.luckyzz.sporegame.menu;

import com.akamecoder.cristalix.menu.MenuGeneration;
import com.akamecoder.cristalix.menu.SingleMenuSession;
import com.luckyzz.sporegame.user.SporeUser;
import org.jetbrains.annotations.NotNull;

class BodyPartsUserMenu extends SingleMenuSession {

    private final SporeUser user;

    BodyPartsUserMenu(@NotNull final SporeUser user) {
        this.user = user;
        openFor(user.getPlayer());
    }

    @Override
    protected void generateMenu(@NotNull final MenuGeneration menu) {

    }

}
