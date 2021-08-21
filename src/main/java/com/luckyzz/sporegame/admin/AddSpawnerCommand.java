package com.luckyzz.sporegame.admin;

import com.akamecoder.cristalix.AkameCristalixException;
import com.akamecoder.cristalix.command.*;
import com.akamecoder.cristalix.command.precondition.ChatCommandPrecondition;
import com.luckyzz.sporegame.food.FoodType;
import com.luckyzz.sporegame.food.category.FoodCategories;
import com.luckyzz.sporegame.food.category.FoodCategory;
import com.luckyzz.sporegame.spawner.SpawnerFactory;
import com.luckyzz.sporegame.spawner.SpawnerType;
import com.luckyzz.sporegame.util.constant.SporeMessages;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class AddSpawnerCommand extends ChatCommand {

    private final SpawnerFactory factory;

    // TODO: Check for world

    private final Map<SpawnerType, CreationStrategy> strategyMap = new HashMap<>();

    AddSpawnerCommand(@NotNull final SpawnerFactory factory) {
        this.factory = factory;

        strategyMap.put(SpawnerType.RANDOM_DELAYED, new RandomDelayedStrategy());

        this.apply(CommandOptions.subCommandOptions().label("add"));
    }

    private void help(@NotNull final CommandSession session) {
        session.getExecutor().send(
                ChatColor.GRAY + "[" + ChatColor.AQUA + "Spore" + ChatColor.GRAY + "] | Помощь по команде /sporeadmin spawner add:\n" +
                        ChatColor.AQUA + "/sporeadmin spawner add [Тип спавнера] [Категория] [Название] [Прочие характеристики]" + ChatColor.GRAY + " - добавить спавнер\n" +
                        ChatColor.AQUA + "  | Тип спавнера - RANDOM_DELAYED(спавнер, который спавнит моба в рандомно сгенирированный период)\n" +
                        ChatColor.AQUA + "  | Категория - тип еды и ее уроверь. Указывается следующим образом: MEAT 1 или GRASS 1\n" +
                        ChatColor.AQUA + "  | Название - любое имя, ни на что не влияет\n" +
                        ChatColor.AQUA + "  | Прочие характерисики - зависят от типа спавнера\n" +
                        ChatColor.AQUA + "  |  RANDOM_DELAYED: минимальная задержка, максимальная задержка, разброс при спавне(максимальное смещение локации)\n" +
                        ChatColor.AQUA + "  |  /sporeadmin spawner add RANDOM_DELAYED MEAT 1 Meat1 15 60 5"
        );
    }

    @Override
    protected void executionPreconditions(@NotNull final PreconditionList list) {
        list.precondition(ChatCommandPrecondition.precondition(session -> session.getArguments().length() >= 4, this::help));
    }

    @Override
    protected void execution(@NotNull final CommandSession session) {
        final Executor executor = session.getExecutor();
        final Arguments args = session.getArguments();

        SpawnerType type;
        try {
            type = SpawnerType.fromString(args.get(1));
        }  catch (final AkameCristalixException exception) {
            executor.send(SporeMessages.ERROR_PREFIX + "Тип спавнера указан неверно! Доступны: RANDOM_DELAYED");
            return;
        }

        FoodType foodType;
        try {
            foodType = FoodType.fromString(args.get(2));
            if(foodType == FoodType.PIECE) {
                throw new AkameCristalixException();
            }
        } catch (final AkameCristalixException exception) {
            executor.send(SporeMessages.ERROR_PREFIX + "Тип еды указан некорректно! Доступны: MEAT и GRASS");
            return;
        }

        int level;
        try {
            level = Integer.parseInt(args.get(3));
        } catch (final NumberFormatException exception) {
            executor.send(SporeMessages.ERROR_PREFIX + "Уровень еды должен быть числом!");
            return;
        }
        final FoodCategory category = FoodCategories.fromRaw(foodType, level);

        final CreationStrategy strategy = strategyMap.get(type);
        if(strategy.create(category, session)) {
            executor.send(SporeMessages.SUCCESS_PREFIX + "Вы успешно создали новый спавнер!");
        }
    }

    private interface CreationStrategy {

        boolean create(@NotNull final FoodCategory category, @NotNull final CommandSession session);

    }

    private class RandomDelayedStrategy implements CreationStrategy {

        @Override
        public boolean create(@NotNull final FoodCategory category, @NotNull final CommandSession session) {
            final Executor executor = session.getExecutor();
            final Arguments arguments = session.getArguments();
            if(arguments.length() != 7) {
                if(arguments.length() >= 4) {
                    executor.send(SporeMessages.ERROR_PREFIX + "Для RANDOM_DELAYED спавнера нужно также указать: минимальную задержку, максимальную задержку, разброс при спавне");
                    return false;
                }
                executor.send(
                        ChatColor.GRAY + "[" + ChatColor.AQUA + "Spore" + ChatColor.GRAY + "] | Помощь по командам /sporeadmin spawner add:\n" +
                                ChatColor.AQUA + "/sporeadmin spawner add [Тип спавнера] [Категория] [Название] [Прочие характеристики]" + ChatColor.GRAY + " - добавить спавнер\n" +
                                ChatColor.AQUA + "  | Тип спавнера - RANDOM_DELAYED(спавнер, который спавнит моба в рандомно сгенирированный период)\n" +
                                ChatColor.AQUA + "  | Категория - тип еды и ее уроверь. Указывается следующим образом: MEAT 1 или GRASS 1\n" +
                                ChatColor.AQUA + "  | Название - любое имя, ни на что не влияет\n" +
                                ChatColor.AQUA + "  | Прочие характерисики - зависят от типа спавнера\n" +
                                ChatColor.AQUA + "  |  RANDOM_DELAYED: минимальная задержка, максимальная задержка, разброс при спавне(максимальное смещение локации)\n" +
                                ChatColor.AQUA + "  |  /sporeadmin spawner add RANDOM_DELAYED MEAT 1 Meat1 15 60 5"
                );
                return false;
            }

            final String name = arguments.get(4);

            int minDelay;
            try {
                minDelay = Integer.parseInt(arguments.get(5));
            } catch (final NumberFormatException exception) {
                session.getExecutor().send(SporeMessages.ERROR_PREFIX + "Минимальная задержка должна быть числом!");
                return false;
            }

            int maxDelay;
            try {
                maxDelay = Integer.parseInt(arguments.get(6));
            } catch (final NumberFormatException exception) {
                session.getExecutor().send(SporeMessages.ERROR_PREFIX + "Максмальная задержка должна быть числом!");
                return false;
            }

            int distance;
            try {
                distance = Integer.parseInt(arguments.get(7));
            } catch (final NumberFormatException exception) {
                session.getExecutor().send(SporeMessages.ERROR_PREFIX + "Разброс при спавне должен быть числом!");
                return false;
            }

            factory.createRandomDelayed(name, executor.getPlayerHandle().getLocation(), category, minDelay, maxDelay, distance);

            return true;
        }

    }

}
