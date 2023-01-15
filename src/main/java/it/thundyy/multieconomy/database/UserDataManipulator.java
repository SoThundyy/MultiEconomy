package it.thundyy.multieconomy.database;

import it.thundyy.multieconomy.EconomyPlugin;
import it.thundyy.multieconomy.currency.registry.CurrencyRegistry;
import it.thundyy.multieconomy.database.enums.Query;
import it.thundyy.multieconomy.database.helper.DataHelper;
import it.thundyy.multieconomy.user.User;
import lombok.RequiredArgsConstructor;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class UserDataManipulator {
    private final EconomyPlugin plugin;

    public void save(User user) {
        CompletableFuture.runAsync(() -> {
            try (Connection connection = plugin.getDatabaseConnector().getConnection()) {
                user.getBalances().forEach((currencyName, amount) -> {
                    DataHelper.flush(connection, Query.UPDATE.getQuery(), user.getUuid().toString(), currencyName, amount);
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, plugin.getExecutor());
    }

    public void register(UUID uuid) {
        CompletableFuture.runAsync(() -> {
            CurrencyRegistry.getInstance().getAll().forEach(currency -> {
                try (Connection connection = plugin.getDatabaseConnector().getConnection()) {
                    DataHelper.prepareStatement(connection, Query.INSERT.getQuery(), uuid.toString(), currency.name(), 0);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }, plugin.getExecutor());
    }

    public void remove() {
        // TODO: 15/01/2023
    }

    public CompletableFuture<User> get(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection connection = plugin.getDatabaseConnector().getConnection();
                 CachedRowSet rowSet = DataHelper.executeQuery(connection, Query.SELECT_ALL.getQuery(), uuid.toString())) {
                User user = new User(uuid);

                while (rowSet.next()) {
                    user.setBalance(rowSet.getString("currency_name"), rowSet.getBigDecimal("amount"));
                }

                return user;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }, plugin.getExecutor());
    }
}
