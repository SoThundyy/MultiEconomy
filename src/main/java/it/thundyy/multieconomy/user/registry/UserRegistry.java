package it.thundyy.multieconomy.user.registry;

import com.google.common.collect.Maps;
import it.thundyy.multieconomy.user.User;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

public class UserRegistry {
    @Getter(lazy = true)
    private static final UserRegistry instance = new UserRegistry();
    private final Map<UUID, User> users = Maps.newHashMap();

    public void registerUser(User user) {
        users.put(user.getUuid(), user);
    }

    public boolean isRegistered(UUID uuid) {
        return users.containsKey(uuid);
    }

    public User removeUser(User user) {
        return users.remove(user.getUuid());
    }

    public User getUser(UUID uuid) {
        return users.get(uuid);
    }
}