package com.revature.quizzard.screens.bank;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.models.BankAccount;
import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.services.BankService;
import com.revature.quizzard.util.ScreenRouter;

import java.io.BufferedReader;

public class OpenAccountScreen extends Screen {

    private final BankService bankService;

    public OpenAccountScreen(BufferedReader consoleReader, ScreenRouter router, BankService bankService) {
        super("LoginScreen", "/open_bank_account", consoleReader, router);
        this.bankService= bankService;
    }

    @Override
    public void render() throws Exception {

        String menu =
                "Please provide which bank account you want to open.\n"+
                "1) Checking Account\n" +
                "2) Saving Account\n" +
                "> ";
        System.out.print(menu);
        String accountTypeSelection = consoleReader.readLine();
        String accountType = "";
        switch (accountTypeSelection) {
            case "1":
                accountType = "Checking";
                break;
            case "2":
                accountType = "Saving";
                break;
            default:
                System.out.println("You have made an invalid selection");
        }

        System.out.print("What name do you want to call it?\n" +
                "> ");

        String accountName = consoleReader.readLine();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountType(accountType);
        bankAccount.setAccountName(accountName);

        try {
            bankService.openBackAccount(bankAccount);
            router.navigate("/dashboard");
        } catch (InvalidRequestException | AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }
}
