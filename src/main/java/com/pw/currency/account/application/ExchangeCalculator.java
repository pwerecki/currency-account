package com.pw.currency.account.application;

import com.pw.currency.account.domain.Currency;
import com.pw.currency.account.domain.Money;

import java.util.List;
import java.util.Set;

public interface ExchangeCalculator {

    List<Money> calculate(Money money, Set<Currency> currencies);
}
