package com.pw.currency.account.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount, Currency currency) {

    public Money subtract(Money input) {
        if (!currency.equals(input.currency)) {
            throw new IllegalArgumentException("Can't subtract different currencies!");
        }

        return new Money(amount.subtract(input.amount), currency);
    }

    public Money exchange(ExchangeRate exchangeRate) {
        if (!exchangeRate.baseCurrency().equals(currency)) {
            throw new IllegalArgumentException("Exchange base currency is different!");
        }

        return new Money(amount.multiply(exchangeRate.rate()).setScale(2, RoundingMode.HALF_UP), exchangeRate.targetCurrency());
    }

    public boolean isLess(Money input) {
        if (!currency.equals(input.currency)) {
            throw new IllegalArgumentException("Can't compare different currencies!");
        }

        return amount.compareTo(input.amount) < 0;
    }
}
