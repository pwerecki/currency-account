package com.pw.currency.account.infrastructure.currency.account.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "currency_account")
class CurrencyAccountEntity {

    @Id
    private String accountId;

    private String ownerFirstName;

    private String ownerLastName;

    private BigDecimal balanceAmount;

    private String balanceCurrency;
}
