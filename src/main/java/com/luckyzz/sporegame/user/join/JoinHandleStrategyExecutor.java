package com.luckyzz.sporegame.user.join;

import com.akamecoder.cristalix.event.handle.AkameQuickListener;
import com.akamecoder.cristalix.event.handle.QuickEvent;
import com.luckyzz.sporegame.user.event.SporeUserJoinEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class JoinHandleStrategyExecutor {

    public JoinHandleStrategyExecutor(@NotNull final Plugin plugin, @NotNull final EventPriority priority,
                                      @NotNull final JoinHandleStrategy strategy) {
        AkameQuickListener.newListener().event(QuickEvent.newBuilder(SporeUserJoinEvent.class)
                .priority(priority)
                .action(event -> strategy.handleUser(event.getUser()))
        ).register(plugin);
    }

}
