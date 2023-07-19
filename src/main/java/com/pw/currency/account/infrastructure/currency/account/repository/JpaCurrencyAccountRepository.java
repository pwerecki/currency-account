package com.pw.currency.account.infrastructure.currency.account.repository;

import com.pw.currency.account.application.CurrencyAccountRepository;
import com.pw.currency.account.domain.CurrencyAccount;
import com.pw.currency.account.domain.AccountId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
class JpaCurrencyAccountRepository implements CurrencyAccountRepository {

    private final CurrencyAccountEntityRepository currencyAccountEntityRepository;

    @Override
    public CurrencyAccount save(CurrencyAccount currencyAccount) {
        var entity = currencyAccountEntityRepository.save(CurrencyAccountMapper.map(currencyAccount));
        return CurrencyAccountMapper.map(entity);
    }

    @Override
    public Optional<CurrencyAccount> findByAccountId(AccountId accountId) {
        return currencyAccountEntityRepository.findById(accountId.id())
                .map(CurrencyAccountMapper::map);
    }
}
