package com.pw.currency.account.presentation;

import com.pw.currency.account.domain.Money;

import java.math.BigDecimal;

record BalanceView(BigDecimal amount, String currency) {

    static BalanceView from(Money balance) {
        return new BalanceView(balance.amount(), balance.currency().name());
    }
}
