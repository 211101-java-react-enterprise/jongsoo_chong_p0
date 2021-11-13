package com.revature.quizzard.screens.bank;

import com.revature.quizzard.services.BankService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;

public class DepositScreen extends DepositWithdrawScreen {
    public DepositScreen(BufferedReader consoleReader, ScreenRouter router, BankService bankService) {
        super(consoleReader, router, bankService,"deposit");
    }
}
