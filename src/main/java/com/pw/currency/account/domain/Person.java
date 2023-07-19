package com.pw.currency.account.domain;


import static org.apache.commons.lang3.StringUtils.isEmpty;

public record Person(String firstName, String lastName) {

    public Person {
        if (isEmpty(firstName)) {
            throw new IllegalArgumentException("first name can't be empty!");
        }

        if (isEmpty(lastName)) {
            throw new IllegalArgumentException("last name can't be empty!");
        }
    }
}
