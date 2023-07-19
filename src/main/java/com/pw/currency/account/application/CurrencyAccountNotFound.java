package com.pw.currency.account.application;

public class CurrencyAccountNotFound extends RuntimeException {
    public CurrencyAccountNotFound(String msg) {
        super(msg);
    }
}
