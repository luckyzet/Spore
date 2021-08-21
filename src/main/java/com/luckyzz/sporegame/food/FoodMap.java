package com.luckyzz.sporegame.food;

import com.luckyzz.sporegame.food.category.FoodCategory;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.Stream;

class FoodMap implements Iterable<SporeFood> {

    private final Multimap<FoodCategory, SporeFood> map = HashMultimap.create();

    FoodMap() {
    }

    public @NotNull Multimap<FoodCategory, SporeFood> getMap() {
        return map;
    }

    public boolean remove(@NotNull final SporeFood food) {
        return map.remove(food.getCategory(), food);
    }

    void put(@NotNull final SporeFood food) {
        map.put(food.getCategory(), food);
    }

    public @NotNull Stream<SporeFood> stream() {
        return map.values().stream();
    }

    @Override
    public @NotNull Iterator<SporeFood> iterator() {
        return map.values().iterator();
    }
}
