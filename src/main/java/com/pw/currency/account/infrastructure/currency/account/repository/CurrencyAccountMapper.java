package com.pw.currency.account.infrastructure.currency.account.repository;

import com.pw.currency.account.domain.CurrencyAccount;
import com.pw.currency.account.domain.AccountId;
import com.pw.currency.account.domain.Currency;
import com.pw.currency.account.domain.Money;
import com.pw.currency.account.domain.Person;
import lombok.experimental.UtilityClass;

@UtilityClass
class CurrencyAccountMapper {

    CurrencyAccountEntity map(CurrencyAccount currencyAccount) {
        return CurrencyAccountEntity.builder()
                .accountId(currencyAccount.getAccountId().id())
                .ownerFirstName(currencyAccount.getOwner().firstName())
                .ownerLastName(currencyAccount.getOwner().lastName())
                .balanceAmount(currencyAccount.getBalance().amount())
                .balanceCurrency(currencyAccount.getBalance().currency().name())
                .build();
    }

    CurrencyAccount map(CurrencyAccountEntity entity) {
        return new CurrencyAccount(
                new AccountId(entity.getAccountId()),
                new Person(entity.getOwnerFirstName(), entity.getOwnerLastName()),
                new Money(entity.getBalanceAmount(), Currency.valueOf(entity.getBalanceCurrency()))
        );
    }
}
