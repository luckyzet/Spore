package com.luckyzz.sporegame.tab;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

public final class TabConstants {

    private TabConstants() {
        throw new UnsupportedOperationException();
    }

    public static final BaseComponent[] HEADER = new ComponentBuilder(new TextComponent(ChatColor.AQUA + "Cristalix")).create();

    public static final BaseComponent[] FOOTER = new ComponentBuilder(new TextComponent(ChatColor.WHITE + "www.cristalix.ru")).create();

}
