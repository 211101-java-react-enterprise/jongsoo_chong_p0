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
import java.text.NumberFormat;

public class ViewTransactionScreen extends Screen {

    private final BankService bankService;

    public ViewTransactionScreen(BufferedReader consoleReader, ScreenRouter router, BankService bankService) {
        super("LoginScreen", "/view_transaction", consoleReader, router);
        this.bankService = bankService;
    }

    @Override
    public void render() throws Exception {

        StringBuilder menu =
                new StringBuilder("\nview the transaction history for an account.\n");
        menu.append("Witch account do you want to view?\n");
        StringBuilder menu2 =
                new StringBuilder("\n\n");

        try {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            List<BankAccount> bankAccountLists = bankService.getBackAccountsByUserId();
            for (int i = 0; i < bankAccountLists.size(); i++) {
                menu.append(i + 1);
                menu.append(") ");
                menu.append(bankAccountLists.get(i).getAccountType());
                menu.append(" - ");
                menu.append(bankAccountLists.get(i).getAccountName());
                menu.append("\n");
            }
            menu.append(bankAccountLists.size() + 1);
            menu.append(") Exit\n");
            menu.append("> ");
            System.out.print(menu);

            String account_selected = consoleReader.readLine();
            int i_account_selected = Integer.parseInt(account_selected);
            if (i_account_selected == bankAccountLists.size() + 1) {
                router.navigate("/dashboard");
            } else if (i_account_selected <= 0 && i_account_selected > bankAccountLists.size() + 1) {
                System.out.println("You have made an invalid selection");
                router.navigate("/dashboard");
            }
            // view the transaction history for an account
            List<BankTransaction> bankTransactions = bankService.getTransactionsByUserAccountId(bankAccountLists.get(i_account_selected - 1));
            menu2.append("Transactions of account : ").append(bankAccountLists.get(i_account_selected - 1).getAccountName()).append("\n");
            for (int i = 0; i < bankTransactions.size(); i++) {
                menu2.append(i + 1);
                menu2.append(". ");
                menu2.append(bankTransactions.get(i).getDate_added());
                menu2.append(" | ");

                menu2.append(formatter.format(bankTransactions.get(i).getAmount()));
                menu2.append("\n");
            }
            System.out.print(menu2);

            router.navigate("/dashboard");

        } catch (InvalidRequestException | AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }
}
