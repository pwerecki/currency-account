package com.pw.currency.account.infrastructure.exchange.rate.finder;

import com.pw.currency.account.application.ExchangeRateFinder;
import com.pw.currency.account.application.ExchangeRateFinderException;
import com.pw.currency.account.domain.Currency;
import com.pw.currency.account.domain.ExchangeRate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@AllArgsConstructor
class NBPExchangeRateFinder implements ExchangeRateFinder {

    @Value("${integration.rateFinder.nbp.defaultTable}")
    private final String defaultTable;
    private final NBPExchangeRateClient nbpExchangeRateClient;

    @Override
    public ExchangeRate findFor(Currency baseCurrency, Currency targetCurrency) {
        if (baseCurrency.equals(targetCurrency)) {
            return new ExchangeRate(baseCurrency, targetCurrency, BigDecimal.ONE);
        }

        if (baseCurrency.equals(Currency.PLN) || targetCurrency.equals(Currency.PLN)) {
            return fetch(baseCurrency, targetCurrency);
        }

        throw new ExchangeRateFinderException("NBP support only exchange rates where target or base currency is PLN!");
    }

    private ExchangeRate fetch(Currency baseCurrency, Currency targetCurrency) {
        if(baseCurrency.equals(Currency.PLN)) {
            var response = nbpExchangeRateClient.getRate(defaultTable, targetCurrency.name());
            return new ExchangeRate(baseCurrency, targetCurrency, inverseExchangeRate(response.rates().get(0).mid()));
        }

        var response = nbpExchangeRateClient.getRate(defaultTable, baseCurrency.name());
        return new ExchangeRate(baseCurrency, targetCurrency, response.rates().get(0).mid());
    }

    private static BigDecimal inverseExchangeRate(BigDecimal rate) {
        return BigDecimal.ONE.divide(rate, 4, RoundingMode.HALF_UP);
    }
}
