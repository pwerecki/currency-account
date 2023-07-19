package com.pw.currency.account.application;

import com.pw.currency.account.domain.Currency;
import com.pw.currency.account.domain.ExchangeRate;

public interface ExchangeRateFinder {

    ExchangeRate findFor(Currency baseCurrency, Currency targetCurrency);
}
