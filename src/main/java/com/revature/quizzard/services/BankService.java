package com.revature.quizzard.services;

import com.revature.quizzard.daos.BankDAO;
import com.revature.quizzard.exceptions.AuthorizationException;
import com.revature.quizzard.exceptions.NotEnoughBalanceException;
import com.revature.quizzard.exceptions.ResourcePersistenceException;
import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.BankAccount;
import com.revature.quizzard.models.BankTransaction;
import com.revature.quizzard.util.collections.List;

public class BankService {

    private final BankDAO bankDAO;
    private final UserService userService;

    public BankService(BankDAO bankDAO, UserService userService) {
        this.bankDAO = bankDAO;
        this.userService = userService;
    }

    public boolean isBankAccountValid(BankAccount bankAccount) {
        if (bankAccount == null) return false;
        if (bankAccount.getAccountName() == null || bankAccount.getAccountName().trim().equals("")) return false;
        if (bankAccount.getAccountType() == null || bankAccount.getAccountType().trim().equals("")) return false;
        return true;
    }

    public boolean openBankAccount(BankAccount bankAccount) {

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        bankAccount.setCreator(userService.getSessionUser());
        BankAccount registeredBankAccount = bankDAO.save(bankAccount);

        if (registeredBankAccount == null) {
            throw new ResourcePersistenceException("The bank account could not be persisted to the datasource!");
        }

        return true;
    }

    public List<BankAccount> getBankAccountsByUserId() {

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        AppUser sessionUser  = userService.getSessionUser();
        String userId = sessionUser.getId();
        List<BankAccount> registeredBankAccounts = bankDAO.findBankAccountsByCreatorId(userId);

        if (registeredBankAccounts == null) {
            throw new ResourcePersistenceException("The bank account could not be persisted to the datasource!");
        }

        return registeredBankAccounts;
    }

    public BankTransaction transact(BankTransaction bankTransaction) {

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        bankTransaction.setTrader(userService.getSessionUser());
        if (bankTransaction.getBankAccountTarget().getBalance() + bankTransaction.getAmount() < 0) {
            throw new NotEnoughBalanceException();
        }

        bankTransaction =  bankDAO.deposit_withdraw(bankTransaction);

        if (bankTransaction == null) {
            throw new ResourcePersistenceException("The transaction could not be persisted to the datasource!");
        }

        return bankTransaction;
    }

    public List<BankTransaction> getTransactionsByUserAccountId(BankAccount bankAccount) {

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        List<BankTransaction> bankTransactions =  bankDAO.findTransactionsByUserAccountId(bankAccount.getBank_account_id());

        if (bankTransactions == null) {
            throw new ResourcePersistenceException("The transactions could not be persisted to the datasource!");
        }

        return bankTransactions;
    }

    public double deposit(BankAccount bankAccount, double amount) {

        return 0;
    }

    public double withdraw(BankAccount bankAccount, double amount) {

        return 0;
    }

    public BankAccount[] viewBalances() {

        return null;
    }
}
