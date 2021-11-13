package com.revature.quizzard.screens.bank;

import com.revature.quizzard.services.BankService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;

public class WithdrawScreen extends DepositWithdrawScreen {
    public WithdrawScreen(BufferedReader consoleReader, ScreenRouter router, BankService bankService) {
        super(consoleReader, router, bankService,"withdraw");
    }
}
