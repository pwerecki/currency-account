package com.pw.currency.account.application;

import com.pw.currency.account.domain.CurrencyAccount;
import com.pw.currency.account.domain.AccountId;

import java.util.Optional;

public interface CurrencyAccountRepository {

    CurrencyAccount save(CurrencyAccount currencyAccount);

    Optional<CurrencyAccount> findByAccountId(AccountId accountId);
}
