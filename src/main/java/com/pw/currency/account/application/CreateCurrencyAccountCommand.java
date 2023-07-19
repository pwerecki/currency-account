package com.pw.currency.account.application;

import com.pw.currency.account.domain.Money;
import com.pw.currency.account.domain.Person;
import lombok.Builder;

@Builder
public record CreateCurrencyAccountCommand(Money initialAmount, Person owner) {
}
