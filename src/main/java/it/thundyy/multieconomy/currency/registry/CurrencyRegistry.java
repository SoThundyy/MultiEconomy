package it.thundyy.multieconomy.currency.registry;

import com.google.common.collect.Maps;
import it.thundyy.multieconomy.currency.Currency;
import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CurrencyRegistry {
    @Getter(lazy = true)
    private static final CurrencyRegistry instance = new CurrencyRegistry();
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

    public Collection<Currency> getAll() {
        return currencies.values();
    }
}
