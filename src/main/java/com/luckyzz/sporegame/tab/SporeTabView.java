package com.luckyzz.sporegame.tab;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.formatting.Formatting;
import ru.cristalix.core.tab.ITabTextFormatter;
import ru.cristalix.core.tab.ITabView;
import ru.cristalix.core.tab.TabHeaderFooter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class SporeTabView implements ITabView {

    private ITabTextFormatter formatter;
    private TabHeaderFooter headerFooter;

    SporeTabView(@NotNull final ITabTextFormatter formatter, @NotNull final TabHeaderFooter headerFooter) {
        this.formatter = formatter;
        this.headerFooter = headerFooter;
    }

    @Override
    public void setFormatter(@NotNull final ITabTextFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void setTabHeaderFooter(@NotNull final TabHeaderFooter headerFooter) {
        this.headerFooter = headerFooter;
    }

    @Override
    public @NotNull TabHeaderFooter getTabHeaderFooter() {
        return headerFooter;
    }

    @Override
    public @NotNull CompletableFuture<BaseComponent> getFormattedComponent(@NotNull final UUID uuid) {
        return formatter == null ?
                CompletableFuture.completedFuture(new TextComponent("")) :
                formatter.format(uuid).thenApply(Formatting::join);
    }

    @Override
    public @NotNull CompletableFuture<String> getOrderingComponent(@NotNull final UUID uuid) {
        return this.formatter.getOrderingComponent(uuid).thenApply(i -> {
            return "§" + i;
        });
    }

}
