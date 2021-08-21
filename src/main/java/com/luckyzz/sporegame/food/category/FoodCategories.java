package com.luckyzz.sporegame.food.category;

import com.luckyzz.sporegame.food.FoodType;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.jetbrains.annotations.NotNull;

public final class FoodCategories {

    private static final Table<FoodType, Integer, FoodCategory> categoryMap = HashBasedTable.create();

    static {
        categoryMap.put(FoodType.GRASS, 1, new FoodCategory(FoodType.GRASS, 1));
        categoryMap.put(FoodType.GRASS, 2, new FoodCategory(FoodType.GRASS, 2));
        categoryMap.put(FoodType.GRASS, 3, new FoodCategory(FoodType.GRASS, 3));
        categoryMap.put(FoodType.GRASS, 4, new FoodCategory(FoodType.GRASS, 4));
        categoryMap.put(FoodType.GRASS, 5, new FoodCategory(FoodType.GRASS, 5));
        categoryMap.put(FoodType.GRASS, 6, new FoodCategory(FoodType.GRASS, 6));
        categoryMap.put(FoodType.GRASS, 7, new FoodCategory(FoodType.GRASS, 7));
        categoryMap.put(FoodType.GRASS, 8, new FoodCategory(FoodType.GRASS, 8));

        categoryMap.put(FoodType.MEAT, 1, new FoodCategory(FoodType.MEAT, 1));
        categoryMap.put(FoodType.MEAT, 2, new FoodCategory(FoodType.MEAT, 2));
        categoryMap.put(FoodType.MEAT, 3, new FoodCategory(FoodType.MEAT, 3));
        categoryMap.put(FoodType.MEAT, 4, new FoodCategory(FoodType.MEAT, 4));
        categoryMap.put(FoodType.MEAT, 5, new FoodCategory(FoodType.MEAT, 5));
        categoryMap.put(FoodType.MEAT, 6, new FoodCategory(FoodType.MEAT, 6));
        categoryMap.put(FoodType.MEAT, 7, new FoodCategory(FoodType.MEAT, 7));
        categoryMap.put(FoodType.MEAT, 8, new FoodCategory(FoodType.MEAT, 8));
    }

    public static @NotNull FoodCategory fromRaw(@NotNull final FoodType type, final int level) {
        return categoryMap.get(type, level);
    }

    private FoodCategories() {
        throw new UnsupportedOperationException();
    }

    public static final FoodCategory GRASS_FIRST = categoryMap.get(FoodType.GRASS, 1);
    public static final FoodCategory GRASS_SECOND = categoryMap.get(FoodType.GRASS, 1);
    public static final FoodCategory GRASS_THIRD = categoryMap.get(FoodType.GRASS, 1);
    public static final FoodCategory GRASS_FOURTH = categoryMap.get(FoodType.GRASS, 1);
    public static final FoodCategory GRASS_FIFTH = categoryMap.get(FoodType.GRASS, 1);
    public static final FoodCategory GRASS_SIXTH = categoryMap.get(FoodType.GRASS, 1);
    public static final FoodCategory GRASS_SEVENTH = categoryMap.get(FoodType.GRASS, 1);
    public static final FoodCategory GRASS_EIGHTH = categoryMap.get(FoodType.GRASS, 1);

    public static final FoodCategory MEAT_FIRST = categoryMap.get(FoodType.MEAT, 1);
    public static final FoodCategory MEAT_SECOND = categoryMap.get(FoodType.MEAT, 2);
    public static final FoodCategory MEAT_THIRD = categoryMap.get(FoodType.MEAT, 3);
    public static final FoodCategory MEAT_FOURTH = categoryMap.get(FoodType.MEAT, 4);
    public static final FoodCategory MEAT_FIFTH = categoryMap.get(FoodType.MEAT, 5);
    public static final FoodCategory MEAT_SIXTH = categoryMap.get(FoodType.MEAT, 6);
    public static final FoodCategory MEAT_SEVENTH = categoryMap.get(FoodType.MEAT, 7);
    public static final FoodCategory MEAT_EIGHTH = categoryMap.get(FoodType.MEAT, 8);

//    public static final FoodCategory GRASS_FIRST = new FoodCategory(FoodType.GRASS, 1);
//    public static final FoodCategory GRASS_SECOND = new FoodCategory(FoodType.GRASS, 2);
//    public static final FoodCategory GRASS_THIRD = new FoodCategory(FoodType.GRASS, 3);
//    public static final FoodCategory GRASS_FOURTH = new FoodCategory(FoodType.GRASS, 4);
//    public static final FoodCategory GRASS_FIFTH = new FoodCategory(FoodType.GRASS, 5);
//    public static final FoodCategory GRASS_SIXTH = new FoodCategory(FoodType.GRASS, 6);
//    public static final FoodCategory GRASS_SEVENTH = new FoodCategory(FoodType.GRASS, 7);
//    public static final FoodCategory GRASS_EIGHTH = new FoodCategory(FoodType.GRASS, 8);
//
//    public static final FoodCategory MEAT_FIRST = new FoodCategory(FoodType.MEAT, 1);
//    public static final FoodCategory MEAT_SECOND = new FoodCategory(FoodType.MEAT, 2);
//    public static final FoodCategory MEAT_THIRD = new FoodCategory(FoodType.MEAT, 3);
//    public static final FoodCategory MEAT_FOURTH = new FoodCategory(FoodType.MEAT, 4);
//    public static final FoodCategory MEAT_FIFTH = new FoodCategory(FoodType.MEAT, 5);
//    public static final FoodCategory MEAT_SIXTH = new FoodCategory(FoodType.MEAT, 6);
//    public static final FoodCategory MEAT_SEVENTH = new FoodCategory(FoodType.MEAT, 7);
//    public static final FoodCategory MEAT_EIGHTH = new FoodCategory(FoodType.MEAT, 8);

}
