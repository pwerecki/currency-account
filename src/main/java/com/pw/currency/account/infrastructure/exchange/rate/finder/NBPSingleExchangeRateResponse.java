package com.pw.currency.account.infrastructure.exchange.rate.finder;

import java.math.BigDecimal;
import java.util.List;

record NBPSingleExchangeRateResponse(List<Rate> rates) {
    record Rate(BigDecimal mid) {}
}
