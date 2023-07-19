package com.pw.currency.account.application;

import com.pw.currency.account.domain.AccountId;
import com.pw.currency.account.domain.Currency;
import com.pw.currency.account.domain.CurrencyAccount;
import com.pw.currency.account.domain.Money;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CurrencyAccountService {
    @Value("${application.currencyAccount.balance.currencies}")
    private final Set<Currency> balanceCurrencies;
    private final CurrencyAccountRepository currencyAccountRepository;
    private final ExchangeRateFinder exchangeRateFinder;
    private final ExchangeCalculator exchangeCalculator;

    @Transactional
    public CurrencyAccountDto create(CreateCurrencyAccountCommand createCurrencyAccountCommand) {
        var currencyAccount = build(createCurrencyAccountCommand);
        currencyAccountRepository.save(currencyAccount);
        return CurrencyAccountDto.from(currencyAccount, calculateBalanceForVariousCurrencies(currencyAccount.getBalance()));
    }

    @Transactional
    public CurrencyAccountDto exchange(ExchangeCurrencyCommand exchangeCurrencyCommand) {
        var currencyAccount = currencyAccountRepository.findByAccountId(exchangeCurrencyCommand.accountId())
                .orElseThrow(() -> new CurrencyAccountNotFound("Account not found!"));

        currencyAccount.exchange(
                exchangeCurrencyCommand.exchange(),
                exchangeRateFinder.findFor(
                        exchangeCurrencyCommand.exchange().currency(),
                        currencyAccount.getBalance().currency()
                )
        );

        currencyAccountRepository.save(currencyAccount);

        return CurrencyAccountDto.from(currencyAccount, calculateBalanceForVariousCurrencies(currencyAccount.getBalance()));
    }

    public CurrencyAccountDto findCurrencyAccount(AccountId accountId) {
        return currencyAccountRepository.findByAccountId(accountId)
                .map(currencyAccount -> CurrencyAccountDto.from(currencyAccount, calculateBalanceForVariousCurrencies(currencyAccount.getBalance())))
                .orElseThrow(() -> new CurrencyAccountNotFound("Account not found!"));
    }

    private List<Money> calculateBalanceForVariousCurrencies(Money balance) {
        return exchangeCalculator.calculate(balance, balanceCurrencies);
    }

    private static CurrencyAccount build(CreateCurrencyAccountCommand createCurrencyAccountCommand) {
        return new CurrencyAccount(
                new AccountId(UUID.randomUUID().toString()),
                createCurrencyAccountCommand.owner(),
                createCurrencyAccountCommand.initialAmount()
        );
    }
}
