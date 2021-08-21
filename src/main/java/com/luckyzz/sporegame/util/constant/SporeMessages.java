package com.luckyzz.sporegame.util.constant;

import com.akamecoder.cristalix.message.Message;
import org.bukkit.ChatColor;

public final class SporeMessages {

    public static final String ERROR_PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "i" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE;
    public static final String SUCCESS_PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "i" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE;
    public static final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "i" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE;
    public static final String MENU_PREFIX = ChatColor.DARK_GRAY + "";
    public static final String HIGHLIGHTED_PREFIX = ChatColor.GOLD + "";
    public static final String DEFAULT_PREFIX = ChatColor.WHITE + "";

    private SporeMessages() {
        throw new UnsupportedOperationException();
    }

    public static final Message SUCCESS_CHARACTER_BUY = Message.create(SUCCESS_PREFIX + "Вы успешно разблокировали " +
            HIGHLIGHTED_PREFIX + "персонажа" + DEFAULT_PREFIX + "!");

    public static final Message FAILED_CHARACTER_BUY = Message.create(ERROR_PREFIX + "Не удалось разблокировать " +
            HIGHLIGHTED_PREFIX + "персонажа" + DEFAULT_PREFIX + ":" + HIGHLIGHTED_PREFIX + "%reason%");

    public static final Message SELECT_CHARACTER_MENU_OPEN = Message.create(SUCCESS_PREFIX + "Вы открыли меню выбора " +
            HIGHLIGHTED_PREFIX + "персонажей" + DEFAULT_PREFIX + "!");

    public static final Message CREATION_CHARACTER_MENU_OPEN = Message.create(PREFIX + "Выберите направление развития Вашего персонажа: " +
            HIGHLIGHTED_PREFIX + "травоядные " + DEFAULT_PREFIX + "или " + HIGHLIGHTED_PREFIX + "плотоядные" + DEFAULT_PREFIX + "!");

    public static final Message CREATION_CHARACTER_NAME_LENGTH = Message.create(ERROR_PREFIX + "Вы превысили максимальное количество символов в названии " +
            HIGHLIGHTED_PREFIX + "персонажа" + DEFAULT_PREFIX + " на %count% символов(а)!");

    public static final Message CREATION_CHARACTER_NAME_SYMBOLS = Message.create(ERROR_PREFIX + "Имя персонажа может содержать только " +
            HIGHLIGHTED_PREFIX + "русские " + DEFAULT_PREFIX + "и " + HIGHLIGHTED_PREFIX + "английские " + DEFAULT_PREFIX + "символы!");

    public static final Message CREATION_CHARACTER_NAME_SPACE = Message.create(ERROR_PREFIX + "Имя персонажа не может содержать " +
            HIGHLIGHTED_PREFIX + "пробел" + DEFAULT_PREFIX + "!");

    public static final Message CREATION_CHARACTER_NAME_USED = Message.create(ERROR_PREFIX + "Персонаж с таким именем уже существует на сервере!");

    public static final Message CREATION_CHARACTER_NAME_TYPE = Message.create(PREFIX + "Введите имя Вашего персонажа! " +
            "Оно может содержать только " + HIGHLIGHTED_PREFIX + "русские " + DEFAULT_PREFIX + "и " + HIGHLIGHTED_PREFIX + "английские " + DEFAULT_PREFIX + "символы и состоять максимум из " + HIGHLIGHTED_PREFIX + "8 символов" + DEFAULT_PREFIX + "!");

    public static final Message LOADING_CHARACTER_WAIT = Message.create(PREFIX + "Идет загрузка персонажа в мир... Ожидайте!");

    public static final Message LOADING_CHARACTER_SUCCESS = Message.create(SUCCESS_PREFIX + "Персонаж успешно загружен в мир! Приятной игры!");

    public static final Message WELCOME_SPORE_INVULNERABILITY = Message.create("\n" +
            ChatColor.AQUA + "Добро пожаловать в мир Spore! У Вас есть 20 секунд неуязвимости и невидимости.\n" +
            ChatColor.AQUA + "Удачной игры!" +
            "\n" + ChatColor.RESET);

    public static final Message FAILED_ONLY_PLAYER = Message.create(ERROR_PREFIX + "Это действие может выполнять только " + HIGHLIGHTED_PREFIX + "игрок" + DEFAULT_PREFIX + "!");

    public static final Message FAILED_PERMISSION = Message.create(ERROR_PREFIX + "У Вас нету прав для выполнения этой команды!");

    public static final Message FAILED_SELECT_WORLD_CHARACTER = Message.create(ERROR_PREFIX + "Вы можете выбрать " + HIGHLIGHTED_PREFIX + "персонажа " + DEFAULT_PREFIX + "только на спавне!");

    public static final Message FAILED_SELECT_WORLD_MENU = Message.create(ERROR_PREFIX + "Вы можете открыть меню " + HIGHLIGHTED_PREFIX + "персонажа " + DEFAULT_PREFIX + "только в игровом мире!");

    public static final Message LOADING_USER = Message.create(PREFIX + "Идет загрузка ваших данных... Ожидайте!");

    public static final Message LOADED_USER = Message.create(SUCCESS_PREFIX + "Ваши данные были успешно загружены! Приятной игры!");

}
