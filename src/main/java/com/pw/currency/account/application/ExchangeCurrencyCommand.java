package com.pw.currency.account.application;

import com.pw.currency.account.domain.AccountId;
import com.pw.currency.account.domain.Money;
import lombok.Builder;

@Builder
public record ExchangeCurrencyCommand(AccountId accountId, Money exchange) {
}
