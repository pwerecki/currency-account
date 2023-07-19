package com.pw.currency.account.presentation;

import com.pw.currency.account.application.CreateCurrencyAccountCommand;
import com.pw.currency.account.application.CurrencyAccountService;
import com.pw.currency.account.application.ExchangeCurrencyCommand;
import com.pw.currency.account.domain.AccountId;
import com.pw.currency.account.domain.Currency;
import com.pw.currency.account.domain.Money;
import com.pw.currency.account.domain.Person;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor

@Controller
@RequestMapping("/api/v1/currency-accounts")
class CurrencyAccountController {

    private final CurrencyAccountService currencyAccountService;


    @PostMapping
    ResponseEntity<CurrencyAccountView> create(@RequestBody CreateCurrencyAccountRequest createCurrencyAccountRequest) {
        var currencyAccountDto = currencyAccountService.create(build(createCurrencyAccountRequest));
        return ResponseEntity.ok(CurrencyAccountView.from(currencyAccountDto));
    }

    @PatchMapping("/{account-id}/exchange")
    ResponseEntity<CurrencyAccountView> exchange(@PathVariable("account-id") String accountId,
                                                 @RequestBody CurrencyAccountExchangeRequest currencyAccountExchangeRequest) {
        var currencyAccountDto = currencyAccountService.exchange(build(accountId, currencyAccountExchangeRequest));
        return ResponseEntity.ok(CurrencyAccountView.from(currencyAccountDto));
    }

    @GetMapping("/{account-id}")
    ResponseEntity<CurrencyAccountView> getAccountDetails(@PathVariable("account-id") String accountId) {
        var currencyAccountDto = currencyAccountService.findCurrencyAccount(new AccountId(accountId));
        return ResponseEntity.ok(CurrencyAccountView.from(currencyAccountDto));
    }

    private static ExchangeCurrencyCommand build(String accountId, CurrencyAccountExchangeRequest currencyAccountExchangeRequest) {
        return ExchangeCurrencyCommand.builder()
                .accountId(
                        new AccountId(accountId)
                )
                .exchange(
                        new Money(currencyAccountExchangeRequest.amount(), Currency.valueOf(currencyAccountExchangeRequest.currency()))
                )
                .build();

    }

    private static CreateCurrencyAccountCommand build(CreateCurrencyAccountRequest createCurrencyAccountRequest) {
        return CreateCurrencyAccountCommand.builder()
                .owner(
                        new Person(createCurrencyAccountRequest.firstName(), createCurrencyAccountRequest.lastName())
                )
                .initialAmount(
                        new Money(createCurrencyAccountRequest.initialBalance(), Currency.valueOf(createCurrencyAccountRequest.currency()))
                )
                .build();

    }
}
