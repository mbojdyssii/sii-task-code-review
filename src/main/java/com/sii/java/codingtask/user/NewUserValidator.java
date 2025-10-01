package com.sii.java.codingtask.user;

import org.springframework.util.StringUtils;

public class NewUserValidator {

    public static void validate(final NewUser newUser) {
        if (!StringUtils.hasText(newUser.getFirstName())) {
            throw new IllegalArgumentException("empty user first name");
        }

        if (!StringUtils.hasText(newUser.getLastName())) {
            throw new IllegalArgumentException("empty user last name");
        }

        if (newUser.getBalance() == null) {
            throw new IllegalArgumentException("field balance is required");
        }
    }

}
