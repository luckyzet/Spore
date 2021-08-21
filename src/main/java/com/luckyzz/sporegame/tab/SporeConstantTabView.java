package com.luckyzz.sporegame.tab;

import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.tab.IConstantTabView;
import ru.cristalix.core.tab.ITabTextComponent;
import ru.cristalix.core.tab.ITabTextFormatter;
import ru.cristalix.core.tab.TabHeaderFooter;

public class SporeConstantTabView extends SporeTabView implements IConstantTabView {

    SporeConstantTabView(@NotNull final ITabTextFormatter formatter, @NotNull final TabHeaderFooter headerFooter) {
        super(formatter, headerFooter);
    }

    @Override
    public void addPrefix(@NotNull final ITabTextComponent iTabTextComponent) {

    }

    @Override
    public void addSuffix(@NotNull final ITabTextComponent iTabTextComponent) {

    }

    @Override
    public void removePrefix(@NotNull final ITabTextComponent iTabTextComponent) {

    }

    @Override
    public void removeSuffix(@NotNull final ITabTextComponent iTabTextComponent) {

    }

}
