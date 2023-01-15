package it.thundyy.multieconomy.user.provider;

import it.thundyy.multieconomy.EconomyPlugin;
import it.thundyy.multieconomy.currency.registry.CurrencyRegistry;
import it.thundyy.multieconomy.user.User;
import it.thundyy.multieconomy.user.UserManager;
import it.thundyy.multieconomy.user.registry.UserRegistry;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class UserProvider {
    private final EconomyPlugin plugin;
    private final UserRegistry registry = UserRegistry.getInstance();

    public void register(UUID uuid) {
        if (registry.isRegistered(uuid)) return;

        plugin.getUserDataManipulator().register(uuid);
    }

    public void load(UUID uuid) {
        if (registry.isRegistered(uuid)) return;
        plugin.getUserDataManipulator().get(uuid).whenComplete((user, throwable) -> {
            if (throwable == null) return;

            if (user.getBalances().isEmpty()) register(uuid);
            registry.registerUser(user);
        });
    }

    public void unLoad(UUID uuid) {
        if (!registry.isRegistered(uuid)) return;

        plugin.getUserDataManipulator().save(getUser(uuid));
    }

    public User getUser(UUID uuid) {
        return registry.getUser(uuid);
    }
}
