package com.sii.java.codingtask.transfer;


public class TransferValidator {

    static void validate(final Transfer transfer) {
        if (transfer.getFromUserId() <= 0) {
            throw new IllegalArgumentException("Invalid user id");
        }

        if (transfer.getToUserId() <= 0) {
            throw new IllegalArgumentException("Invalid user id");
        }

        if (transfer.getFromUserId().equals(transfer.getToUserId())) {
            throw new IllegalArgumentException("Transfer from same user");
        }

        if (transfer.getAmount() <= 0) {
            throw new IllegalArgumentException("Transfer amount smaller or equals than 0");
        }
    }

}
