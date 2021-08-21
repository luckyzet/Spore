package com.luckyzz.sporegame;

import com.akamecoder.cristalix.AkameCristalixAPI;
import com.akamecoder.cristalix.scheduler.SchedulerService;
import com.luckyzz.sporegame.admin.SporeAdminCommand;
import com.luckyzz.sporegame.food.SporeFoodManagement;
import com.luckyzz.sporegame.spawner.SpawnerManagement;
import com.luckyzz.sporegame.spawning.SpawningLocationManagement;
import com.luckyzz.sporegame.chat.SporeChatManagement;
import com.luckyzz.sporegame.config.SettingConfig;
import com.luckyzz.sporegame.util.invulnerability.InvulnerabilityService;
import com.luckyzz.sporegame.util.invulnerability.TimedInvulnerabilityService;
import com.luckyzz.sporegame.menu.MenuCommand;
import com.luckyzz.sporegame.select.SelectionCharacterManagement;
import com.luckyzz.sporegame.sidebar.SporeSidebarManagement;
import com.luckyzz.sporegame.tab.SporeTabService;
import com.luckyzz.sporegame.user.SporeUserManagement;
import com.luckyzz.sporegame.user.SporeUserService;
import com.luckyzz.sporegame.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.CoreApi;
import ru.cristalix.core.chat.IChatService;
import ru.cristalix.core.datasync.EntityDataParameters;
import ru.cristalix.core.inventory.IInventoryService;
import ru.cristalix.core.inventory.InventoryService;
import ru.cristalix.core.invoice.IInvoiceService;
import ru.cristalix.core.invoice.InvoiceService;
import ru.cristalix.core.permissions.IPermissionService;
import ru.cristalix.core.realm.IRealmService;
import ru.cristalix.core.realm.RealmId;
import ru.cristalix.core.realm.RealmInfo;
import ru.cristalix.core.realm.RealmStatus;
import ru.cristalix.core.tab.ITabService;

import java.util.Arrays;

public final class SporeGamePlugin extends JavaPlugin {

    /**
     * Запускать ивенты
     * Сделать возможность получасть след уровень игрока (хз как пока, но подумать)
     * Просчитывать ДНК до след уровня
     * Сделать калькулятор убийства еды
     * Сделать репозиторий
     * Переделать все на комфортную работу без репозитория (как апи)
     * В Invulnerability добавить опцию не двигаться done
     * Соеденить всю систему воедино
     *
     * Respawn processor
     * Ставить скины на игрока при загрузке перса
     * Spore item переделать, там specifications for item make and logic to do
     *
     * Сделать эту систему с world logic и item logic так
     * World logic содержит какой-нибудь item logic processor который по юзеру или предмету определяет логику предмета и выполняет ее
     * В Spawn просто отменять парашу всю эту
     * Насчет ебания с логикой, мб сделать создание логики в SporeUserManagement и там сетать логику эту мб
     *
     * Tab переделать display name for player
     * Food change level event cancel
     * Chat fix
     *
     * Под Level сделать систему js скрипта с выводом инфы хуенфы
     */

    private InvulnerabilityService invulnerabilityService;
    private SporeUserManagement userManagement;
    private SpawningLocationManagement spawningLocationManagement;
    private SporeFoodManagement foodManagement;
    private SpawnerManagement spawnerManagement;

    private @NotNull CoreApi initializeCristalixService() {
        Log.info("Initializing cristalix core...");

        final CoreApi coreApi = CoreApi.get();
        coreApi.unregisterService(IChatService.class);
        coreApi.unregisterService(ITabService.class);

        coreApi.registerService(IInventoryService.class, new InventoryService());
        coreApi.registerService(IInvoiceService.class, new InvoiceService(CoreApi.get().getSocketClient()));
        final RealmInfo info = IRealmService.get().getCurrentRealmInfo();
        info.setGroupName("Spore");
        final RealmId realmId = info.getRealmId();
        String readableName = "Spore";

        if (realmId.getId() > 0) {
            readableName += " #" + realmId.getId();
        } else {
            readableName += " #" + (realmId.getId() * -1) + " (Test)";
        }
        info.setReadableName(readableName);
        info.setStatus(RealmStatus.WAITING_FOR_PLAYERS);

        final IPermissionService permissionService = IPermissionService.get();
        permissionService.setDefaultPermissions(Arrays.asList(
                "worldedit.calc",
                "minecraft.*",
                "minecraft.tell",
                "bukkit.command.tell",
                "bukkit.command.say",
                "bukkit.command.me",
                "bukkit.command.plugins"
        ), false);

        EntityDataParameters.register();

        Log.info("Cristalix core successfully initialized!");

        return coreApi;
    }

    private @NotNull AkameCristalixAPI initializeAkameService() {
        Log.info("Initializing akame core...");

        final AkameCristalixAPI akameApi = Bukkit.getServicesManager().load(AkameCristalixAPI.class);
        akameApi.getMenuService().getService(this);

        Log.info("Akame core successfully initialized!");

        return akameApi;
    }

    @Override
    public void onEnable() {
        Log.info("---------------------------------");
        Log.info(" Enabling the spore game plugin...");
        Log.info("---------------------------------");

        final CoreApi coreApi = initializeCristalixService();
        final AkameCristalixAPI akameApi = initializeAkameService();
        final SchedulerService schedulerService = akameApi.getSchedulerService().getService(this);

        final SettingConfig config = new SettingConfig(this);

        spawningLocationManagement = new SpawningLocationManagement(this, config, schedulerService.getRunner());

        userManagement = new SporeUserManagement(this, akameApi.getDatabaseService(), schedulerService.getRunner(), config,
                spawningLocationManagement);

        final SporeUserService userService = userManagement.getUserService();

        foodManagement = new SporeFoodManagement(this, config, userService);
        spawnerManagement = new SpawnerManagement(this, schedulerService.getRunner(), foodManagement.getProcessor());

        coreApi.registerService(ITabService.class, new SporeTabService(userService));

        invulnerabilityService = new TimedInvulnerabilityService(this);
        new SporeChatManagement(this, userService);
        new SporeSidebarManagement(this, akameApi.getSidebarService().getService(this));

        new SporeAdminCommand(spawningLocationManagement, spawnerManagement);
        new MenuCommand(userService);

        new SelectionCharacterManagement(userService, userManagement.getRepository().characterRepository(),
                coreApi.getService(IInvoiceService.class), akameApi.getChatInputService().getService(this), akameApi.getChatClickService().getService(this),
                userManagement.getSessionFactory(), userManagement.getCharacterFactory(), invulnerabilityService);

        Log.info("-----------------------------------");
        Log.info(" The spore game plugin was enabled!");
        Log.info("-----------------------------------");
    }

    @Override
    public void onDisable() {
        userManagement.cancel();
        invulnerabilityService.cancel();
        spawningLocationManagement.cancel();
        spawnerManagement.cancel();
        foodManagement.cancel();
    }

}
