package com.revature.quizzard.services;

import com.revature.quizzard.daos.BankDAO;
import com.revature.quizzard.exceptions.AuthorizationException;
import com.revature.quizzard.exceptions.NotEnoughBalanceException;
import com.revature.quizzard.models.BankAccount;
import com.revature.quizzard.models.BankTransaction;
import com.revature.quizzard.util.collections.List;

public class BankService {

    private final BankDAO bankDAO ;
    private final UserService userService;

    public BankService( BankDAO bankDAO, UserService userService) {
        this.bankDAO = bankDAO;
        this.userService = userService;
    }

    public BankAccount openBackAccount(BankAccount bankAccount){

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        bankAccount.setCreator(userService.getSessionUser());
        return bankDAO.save( bankAccount);
    }

    public List<BankAccount> getBackAccountsByUserId(){

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        return bankDAO.findBankAccountsByCreatorId(userService.getSessionUser().getId());
    }

    public BankTransaction transact(BankTransaction bankTransaction){

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        bankTransaction.setTrader(userService.getSessionUser());
        if( bankTransaction.getBankAccountTo().getBalance() + bankTransaction.getAmount() < 0){
            throw new NotEnoughBalanceException();
        }
        return bankDAO.transact_bank( bankTransaction);
    }

    public List<BankTransaction> getTransactionsByUserAccountId(BankAccount bankAccount){

        if (!userService.isSessionActive()) {
            throw new AuthorizationException("No active user session to perform operation!");
        }

        return bankDAO.findTransactionsByUserAccountId(bankAccount.getBank_account_id());
    }

    public double deposit(BankAccount bankAccount, double amount){

        return 0;
    }

    public double withdraw(BankAccount bankAccount, double amount){

        return 0;
    }

    public  BankAccount[] viewBalances(){

        return null;
    }
}
