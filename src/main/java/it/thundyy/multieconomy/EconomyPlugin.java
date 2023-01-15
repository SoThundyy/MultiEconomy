package it.thundyy.multieconomy;

import it.thundyy.multieconomy.currency.provider.CurrencyProvider;
import it.thundyy.multieconomy.database.DatabaseConnector;
import it.thundyy.multieconomy.database.enums.DatabaseResponse;
import it.thundyy.multieconomy.user.UserManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;

@Getter
public final class EconomyPlugin extends JavaPlugin {

    private final Executor executor = command -> Bukkit.getScheduler().runTaskAsynchronously(this, command);

    // Providers
    private CurrencyProvider currencyProvider;

    // Managers
    private UserManager userManager;
    private DatabaseConnector databaseConnector;

    @Override
    public void onEnable() {
        init();
        initDatabase();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void init() {
        currencyProvider = new CurrencyProvider();

        userManager = new UserManager();
    }

    private void initDatabase() {
        databaseConnector = new DatabaseConnector("multieconomy", "", "root");

        if (databaseConnector.connect() == DatabaseResponse.OK) {
            getLogger().info("Database connection established!");
            databaseConnector.createTables();
        } else {
            getLogger().severe("Database connection failed!");
        }
    }
}
