package com.pw.currency.account.application;

import com.pw.currency.account.domain.AccountId;
import com.pw.currency.account.domain.CurrencyAccount;
import com.pw.currency.account.domain.Money;
import com.pw.currency.account.domain.Person;
import lombok.Builder;

import java.util.List;

@Builder
public record CurrencyAccountDto(AccountId accountId, Person owner, List<Money> balances) {

    public static CurrencyAccountDto from(CurrencyAccount currencyAccount, List<Money> balances) {
        return CurrencyAccountDto.builder()
                .accountId(currencyAccount.getAccountId())
                .owner(currencyAccount.getOwner())
                .balances(balances)
                .build();
    }
}
