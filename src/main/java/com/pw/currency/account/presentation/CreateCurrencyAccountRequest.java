package com.pw.currency.account.presentation;

import java.math.BigDecimal;

record CreateCurrencyAccountRequest(String currency,
                                    BigDecimal initialBalance,
                                    String firstName,
                                    String lastName) {
}
