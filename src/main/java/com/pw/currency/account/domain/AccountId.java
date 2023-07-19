package com.pw.currency.account.domain;

import java.util.Objects;

public record AccountId(String id) {

    public AccountId {
        Objects.requireNonNull(id);
    }
}
