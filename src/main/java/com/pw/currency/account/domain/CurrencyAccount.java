package com.pw.currency.account.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyAccount {

    private final AccountId accountId;
    private final Person owner;
    private Money balance;

    public CurrencyAccount(AccountId accountId, Person owner, Money balance) {
        this.accountId = accountId;
        this.owner = owner;

        if (balance.isLess(new Money(BigDecimal.ZERO, balance.currency()))) {
            throw new IllegalArgumentException("Initial balance can't be less than 0!");
        }

        this.balance = balance;
    }

    public void exchange(Money exchangeAmount, ExchangeRate exchangeRate) {
        if (!balance.currency().equals(exchangeRate.targetCurrency())) {
            throw new CurrencyExchangeException("Wrong target currency of exchange rate!");
        }

        if (!exchangeAmount.currency().equals(exchangeRate.baseCurrency())) {
            throw new CurrencyExchangeException("Wrong base currency of exchange rate!");
        }

        var withdrawAmount = exchangeAmount.exchange(exchangeRate);

        withdraw(withdrawAmount);
    }

    private void withdraw(Money withdrawAmount) {
        if (balance.isLess(withdrawAmount)) {
            throw new CurrencyExchangeException("Insufficient funds in the account!");
        }

        this.balance = balance.subtract(withdrawAmount);
    }
}