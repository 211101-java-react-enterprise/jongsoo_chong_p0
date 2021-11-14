package com.revature.quizzard.screens.bank;

import com.revature.quizzard.exceptions.AuthenticationException;
import com.revature.quizzard.exceptions.InvalidRequestException;
import com.revature.quizzard.exceptions.NotEnoughBalanceException;
import com.revature.quizzard.models.BankAccount;
import com.revature.quizzard.models.BankTransaction;
import com.revature.quizzard.screens.Screen;
import com.revature.quizzard.services.BankService;
import com.revature.quizzard.util.ScreenRouter;
import com.revature.quizzard.util.collections.List;

import java.io.BufferedReader;

public class DepositWithdrawScreen extends Screen {

    private final BankService bankService;
    private String deposit_or_withdraw;

    public DepositWithdrawScreen(BufferedReader consoleReader, ScreenRouter router, BankService bankService, String deposit_or_withdraw) {
        super("LoginScreen", "/"+deposit_or_withdraw, consoleReader, router);
        this.bankService = bankService;
        this.deposit_or_withdraw = deposit_or_withdraw;
    }

    public String getDeposit_or_withdraw() {
        return deposit_or_withdraw;
    }

    public void setDeposit_or_withdraw(String deposit_or_withdraw) {
        this.deposit_or_withdraw = deposit_or_withdraw;
    }

    @Override
    public void render() throws Exception {

        StringBuilder menu =
                new StringBuilder("\nPlease provide which bank account you want to transact.\n");

        try {
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

            System.out.print("\nHow much do you want to " + getDeposit_or_withdraw() + "?\n" + "> ");
            String amountInput = consoleReader.readLine();
            double amount = Double.parseDouble(amountInput);

            BankTransaction bankTransaction = new BankTransaction();
            bankTransaction.setBank_account_id(bankAccountLists.get(Integer.parseInt(account_selected) - 1).getBank_account_id());
            if (deposit_or_withdraw.equals("withdraw")) {
                amount = -amount;
            }
            bankTransaction.setAmount(amount);
            bankTransaction.setBankAccount(bankAccountLists.get(Integer.parseInt(account_selected) - 1));

            try {
                bankTransaction = bankService.transact(bankTransaction);
                router.navigate("/dashboard");
            } catch (NotEnoughBalanceException e) {
                System.out.println("------------------------------------------------\n");
                System.out.println("Not enough balance");
                System.out.println("------------------------------------------------\n");
                router.navigate("/dashboard");
            } catch (InvalidRequestException | AuthenticationException e) {
                System.out.println(e.getMessage());
            }
        } catch (InvalidRequestException | AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }
}
