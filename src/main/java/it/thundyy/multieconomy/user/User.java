package it.thundyy.multieconomy.user;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class User {
    private final UUID uuid;
    private final Map<String, BigDecimal> balances = Maps.newHashMap();

    public BigDecimal getBalance(String currency) {
        return balances.getOrDefault(currency, BigDecimal.ZERO);
    }

    public void setBalance(String currency, BigDecimal balance) {
        balances.replace(currency, balance);
    }
}
