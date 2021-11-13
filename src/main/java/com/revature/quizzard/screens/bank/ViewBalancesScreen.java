package com.revature.quizzard.screens.bank;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.models.BankAccount;
import com.revature.quizzard.models.BankTransaction;
import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.services.BankService;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.collections.List;

import java.io.BufferedReader;

public class ViewBalancesScreen extends Screen {

    private final BankService bankService;

    public ViewBalancesScreen(BufferedReader consoleReader, ScreenRouter router, BankService bankService) {
        super("LoginScreen", "/view_balances", consoleReader, router);
        this.bankService = bankService;
    }

    @Override
    public void render() throws Exception {

        StringBuilder menu =
                new StringBuilder("\nView the balance of my account(s)\n");

        try {
            List<BankAccount> bankAccountLists = bankService.getBackAccountsByUserId();
            for (int i = 0; i < bankAccountLists.size(); i++) {
                menu.append(i + 1);
                menu.append(") ");
                menu.append(bankAccountLists.get(i).getAccountType());
                menu.append(" - ");
                menu.append(bankAccountLists.get(i).getAccountName());
                menu.append(" - $");
                menu.append(bankAccountLists.get(i).getBalance());
                menu.append("\n");
            }

            System.out.print(menu);

            router.navigate("/dashboard");

        } catch (InvalidRequestException | AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }
}
