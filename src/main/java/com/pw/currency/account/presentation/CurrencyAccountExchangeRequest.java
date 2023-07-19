package com.pw.currency.account.presentation;

import java.math.BigDecimal;

record CurrencyAccountExchangeRequest(BigDecimal amount, String currency) {
}
