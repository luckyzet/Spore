package com.luckyzz.sporegame.tab;

import com.akamecoder.cristalix.event.PlayerQuitServerEvent;
import com.akamecoder.cristalix.event.handle.AkameQuickListener;
import com.akamecoder.cristalix.event.handle.QuickEvent;
import com.akamecoder.cristalix.scheduler.SchedulerTicks;
import com.luckyzz.sporegame.user.SporeUserService;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import ru.cristalix.core.BukkitCorePlugin;
import ru.cristalix.core.internal.BukkitInternals;
import ru.cristalix.core.tab.IConstantTabView;
import ru.cristalix.core.tab.ITabService;
import ru.cristalix.core.tab.ITabView;
import ru.cristalix.core.tab.TabHeaderFooter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SporeTabService implements ITabService {

    private final SporeUserService userService;
    private ITabView tabView;

    private boolean updating;
    private BukkitTask task;

    private final Map<UUID, ITabView> viewMap = new HashMap<>();
    private final Map<UUID, TabCache> cacheMap = new ConcurrentHashMap<>(32, 0.75f, 2);

    private final BukkitInternals internals = BukkitInternals.internals();

    public SporeTabService(@NotNull final SporeUserService userService) {
        this.userService = userService;
    }

    private static class TabCache {

        private TabHeaderFooter headerFooter;
        private BaseComponent listName;
        private String orderingComponent;
        private boolean updating;

        private TabCache() {
        }

    }

    @Override
    public void enable() {
        final Plugin plugin = BukkitCorePlugin.getPlugin(BukkitCorePlugin.class);
        tabView = createTabView();

        AkameQuickListener.newListener().event(QuickEvent.newBuilder(PlayerJoinEvent.class).priority(EventPriority.MONITOR).action(event -> {
            final Player player = event.getPlayer();
            if(player.isOnline() && !updating) {
                Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> update(player));
            }
        })).event(PlayerQuitServerEvent.class, event -> {
            final Player player = event.getPlayer();
            final UUID uuid = player.getUniqueId();
            cacheMap.remove(uuid);
            viewMap.remove(uuid);
        }).register(plugin);

        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            updating = true;
            Bukkit.getOnlinePlayers().forEach(this::update);
            updating = false;
        }, 0, SchedulerTicks.toTicks(2, TimeUnit.SECONDS));
    }

    @Override
    public void disable() {
        cacheMap.clear();
        viewMap.clear();
        task.cancel();
    }

    @Override
    public @NotNull ITabView getDefaultTabView() {
        return tabView;
    }

    @Override
    public void setDefaultTabView(@NotNull final ITabView tabView) {
        this.tabView = tabView;
    }

    @Override
    public @NotNull ITabView createTabView() {
        return new SporeTabView(new SporeTabTextFormatter(userService), TabHeaderFooter.of(TabConstants.HEADER, TabConstants.FOOTER));
    }

    @Override
    public @NotNull IConstantTabView createConstantTabView() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTabView(@NotNull final UUID uuid, @NotNull final ITabView tabView) {
        viewMap.compute(uuid, (uuid1, view) -> tabView);
    }

    @Override
    public @NotNull ITabView getTabView(@NotNull final UUID uuid) {
        return viewMap.getOrDefault(uuid, tabView);
    }

    @Override
    public void update(@NotNull final Player player) {
        if(!player.isOnline()) {
            return;
        }
        final UUID uuid = player.getUniqueId();
        TabCache cache = cacheMap.get(uuid);
        final boolean needsCreate = cache == null;

        if(!needsCreate && cache.updating) {
            return;
        }
        final ITabView tabView = getTabView(uuid);
        final TabHeaderFooter headerFooter = tabView.getTabHeaderFooter();
        if(needsCreate || !headerFooter.equals(cache.headerFooter)) {
            if(cache == null) {
                cache = new TabCache();
                cache.headerFooter = headerFooter;
                cacheMap.put(uuid, cache);
            }
            player.setPlayerListHeaderFooter(headerFooter.getHeader(), headerFooter.getFooter());
        }
        cache.updating = true;

        final TabCache finalCache = cache;
        tabView.getFormattedComponent(uuid).thenCompose(formatted -> {
            return tabView.getOrderingComponent(uuid).thenAccept(ordering -> {
                if(formatted.equals(finalCache.listName) && ordering.equals(finalCache.orderingComponent)) {
                    return;
                }
                finalCache.listName = formatted;
                finalCache.orderingComponent = ordering;
                internals.setPlayerListName(player, ordering + TextComponent.toLegacyText(formatted));
            }).thenRun(() -> {
                finalCache.updating = false;
            });
        });
    }


}
