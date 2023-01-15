package it.thundyy.multieconomy.user;

import it.thundyy.multieconomy.currency.registry.CurrencyRegistry;

import java.math.BigDecimal;
import java.math.BigInteger;

public class UserManager {

    public BigDecimal pay(User user, String name, BigDecimal amount) {
        if (!CurrencyRegistry.getInstance().isRegistered(name)
                || user == null) return null;
        BigDecimal userAmount = user.getBalance(name);
        BigDecimal maxCurrencyAmount = CurrencyRegistry.getInstance().getCurrency(name).max();

        BigDecimal toAdd = amount;
        if (userAmount.add(amount).compareTo(maxCurrencyAmount) > 0) {
            user.setBalance(name, maxCurrencyAmount);
            toAdd = maxCurrencyAmount.subtract(userAmount);
        }

        user.setBalance(name, userAmount.add(toAdd));
        return toAdd;
    }

    public BigDecimal take(User user, String name, BigDecimal amount) {
        if (!CurrencyRegistry.getInstance().isRegistered(name)
                || user == null) return null;
        BigDecimal userAmount = user.getBalance(name);
        BigDecimal minCurrencyAmount = CurrencyRegistry.getInstance().getCurrency(name).min();

        BigDecimal toSubtract = amount;
        if (userAmount.subtract(amount).compareTo(minCurrencyAmount) < 0) {
            user.setBalance(name, minCurrencyAmount);
            toSubtract = userAmount.subtract(minCurrencyAmount);
        }

        user.setBalance(name, userAmount.subtract(toSubtract));
        return toSubtract;
    }
}
