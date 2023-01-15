package it.thundyy.multieconomy.currency;

import java.math.BigDecimal;

public interface Currency {

    String name();

    BigDecimal max();

    BigDecimal min();

}
