package it.thundyy.multieconomy.currency.registry;

import com.google.common.collect.Maps;
import it.thundyy.multieconomy.currency.Currency;
import lombok.Getter;

import java.util.Map;

public class CurrencyRegistry {
    private static CurrencyRegistry instance;
    private final Map<String, Currency> currencies = Maps.newHashMap();

    private CurrencyRegistry() {
    }

    public void registerCurrency(Currency currency) {
        currencies.put(currency.name(), currency);
    }

    public Currency getCurrency(String name) {
        return currencies.get(name);
    }

    public boolean isRegistered(String name) {
        return currencies.containsKey(name);
    }

    public void unregisterCurrency(String name) {
        currencies.remove(name);
    }

    public static synchronized CurrencyRegistry getInstance() {
        if (instance == null) {
            instance = new CurrencyRegistry();
        }
        return instance;
    }
}
