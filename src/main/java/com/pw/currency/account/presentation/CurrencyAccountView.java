package com.pw.currency.account.presentation;

import com.pw.currency.account.application.CurrencyAccountDto;
import lombok.Builder;

import java.util.List;

@Builder
record CurrencyAccountView(String accountId,
                           String firstName,
                           String lastName,
                           List<BalanceView> balances) {

    static CurrencyAccountView from(CurrencyAccountDto currencyAccountDto) {
        return CurrencyAccountView.builder()
                .accountId(currencyAccountDto.accountId().id())
                .firstName(currencyAccountDto.owner().firstName())
                .lastName(currencyAccountDto.owner().lastName())
                .balances(
                        currencyAccountDto.balances()
                                .stream()
                                .map(BalanceView::from)
                                .toList()
                )
                .build();
    }
}
