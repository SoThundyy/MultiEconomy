package it.thundyy.multieconomy.user.provider;

import it.thundyy.multieconomy.currency.registry.CurrencyRegistry;
import it.thundyy.multieconomy.user.User;
import it.thundyy.multieconomy.user.UserManager;
import it.thundyy.multieconomy.user.registry.UserRegistry;

import java.util.Optional;
import java.util.UUID;

public class UserProvider {
    private final UserRegistry registry = UserRegistry.getInstance();

    public User register(UUID uuid) {
        User user = new User(uuid);
        registry.registerUser(user);

       return user;
    }

    public Optional<User> load(UUID uuid) {
        User user;
        if (registry.isRegistered(uuid)) return Optional.ofNullable(registry.getUser(uuid));
        return Optional.of(register(uuid));
    }
}
