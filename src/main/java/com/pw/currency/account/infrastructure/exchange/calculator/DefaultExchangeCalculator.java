package com.pw.currency.account.infrastructure.exchange.calculator;

import com.pw.currency.account.application.ExchangeCalculator;
import com.pw.currency.account.application.ExchangeRateFinder;
import com.pw.currency.account.domain.Currency;
import com.pw.currency.account.domain.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
class DefaultExchangeCalculator implements ExchangeCalculator {

    private final ExchangeRateFinder exchangeRateFinder;

    //For better performance we should fetch in 1 request all needed exchange rates and iterate over them
    @Override
    public List<Money> calculate(Money money, Set<Currency> currencies) {
        return currencies.stream()
                .map(currency -> calculate(money, currency))
                .toList();
    }

    private Money calculate(Money money, Currency currency) {
        if (currency.equals(money.currency())) {
            return money;
        }

        var exchangeRate = exchangeRateFinder.findFor(money.currency(), currency);
        return money.exchange(exchangeRate);
    }
}