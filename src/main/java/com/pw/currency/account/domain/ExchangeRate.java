package com.pw.currency.account.domain;

import java.math.BigDecimal;

public record ExchangeRate(Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
}
