package com.luckyzz.sporegame.user;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

class CacheSporeUserService implements SporeUserService {

    private final Map<String, CompletableFuture<SporeUser>> userMap = new HashMap<>();
    private final Function<String, CompletableFuture<SporeUser>> function;

    CacheSporeUserService(@NotNull final Function<String, CompletableFuture<SporeUser>> function) {
        this.function = function;
    }

    @Override
    public @NotNull CompletableFuture<SporeUser> loadUser(@NotNull final String name) {
        if(userMap.containsKey(name)) {
            return userMap.get(name);
        }
        final CompletableFuture<SporeUser> future = function.apply(name);
        userMap.put(name, future);
        return future;
    }

    @Override
    public void unloadUser(@NotNull final String name) {
        userMap.remove(name);
    }

    @Override
    public @NotNull SporeUser getUser(@NotNull final String name) {
        if(!userMap.containsKey(name)) {
            return loadUser(name).join();
        }
        return userMap.getOrDefault(name, CompletableFuture.completedFuture(new EmptySporeUser(name))).join();
    }

}
