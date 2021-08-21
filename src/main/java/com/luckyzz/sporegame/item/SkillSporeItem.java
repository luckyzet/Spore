package com.luckyzz.sporegame.item;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface SkillSporeItem extends SporeItem {

    @NotNull Collection<SporeItemType> getRequiredItems();

}
