package com.sii.java.codingtask.transfer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransferValidatorTest {

    @Test
    public void shouldNotThrowExceptionTransferIsValid() {
        Transfer transfer = new Transfer(1L, 2L, 2D);
        TransferValidator.validate(transfer);
    }

    @Test
    public void shouldThrowExceptionIncorrectFromUserId() {
        Transfer transfer = new Transfer(-1L, 2L, 2D);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransferValidator.validate(transfer);
        });
    }

    @Test
    public void shouldThrowExceptionFromUserIdIsZero() {
        Transfer transfer = new Transfer(0L, 2L, 2D);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransferValidator.validate(transfer);
        });
    }

    @Test
    public void shouldThrowExceptionToUserIdIsZero() {
        Transfer transfer = new Transfer(1L, 0L, 2D);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransferValidator.validate(transfer);
        });
    }

    @Test
    public void shouldThrowExceptionIncorrectToUserId() {
        Transfer transfer = new Transfer(1L, -1L, 2D);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransferValidator.validate(transfer);
        });
    }

    @Test
    public void shouldThrowExceptionFromUserIdIsEqualsToUserId() {
        Transfer transfer = new Transfer(1L, 1L, 2D);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransferValidator.validate(transfer);
        });
    }

    @Test
    public void shouldThrowExceptionTransferUserIsZero() {
        Transfer transfer = new Transfer(1L, 2L, 0D);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransferValidator.validate(transfer);
        });
    }

    @Test
    public void shouldThrowExceptionTransferUserIsNegative() {
        Transfer transfer = new Transfer(1L, 2L, -2D);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TransferValidator.validate(transfer);
        });
    }
}
