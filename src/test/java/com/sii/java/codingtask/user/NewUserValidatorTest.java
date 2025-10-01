package com.sii.java.codingtask.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class NewUserValidatorTest {

    private static final Double TEST_USER_BALANCE = 10D;
    private static final String TEST_LAST_NAME = "testLastName";
    private static final String TEST_FIRST_NAME = "testFirstName";

    @Test
    public void shouldNotThrowExceptionUserIsValid() {
        NewUser newUser = new NewUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);

        NewUserValidator.validate(newUser);
    }

    @Test
    public void shouldThrowExceptionBalanceIsNull() {
        NewUser newUser = new NewUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        newUser.setBalance(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NewUserValidator.validate(newUser);
        });
    }


    @Test
    public void shouldThrowExceptionFirstNameIsEmpty() {
        NewUser newUser = new NewUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        newUser.setFirstName("");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NewUserValidator.validate(newUser);
        });
    }

    @Test
    public void shouldThrowExceptionLastNameIsEmpty() {
        NewUser newUser = new NewUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        newUser.setLastName("");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            NewUserValidator.validate(newUser);
        });
    }
}
