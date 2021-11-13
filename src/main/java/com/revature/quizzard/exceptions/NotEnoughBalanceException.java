package com.revature.quizzard.exceptions;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException() {
        super("Not enough balance");
    }
}
