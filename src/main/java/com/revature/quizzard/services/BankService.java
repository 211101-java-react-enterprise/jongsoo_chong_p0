package com.revature.quizzard.services;

import com.revature.quizzard.daos.AppUserDAO;
import com.revature.quizzard.daos.BankDAO;
import com.revature.quizzard.models.Account;
import com.revature.quizzard.models.AppUser;

public class BankService {

    private final BankDAO bankDAO ;
    private AppUser sessionUser;

    public BankService(AppUserDAO userDAO, BankDAO bankDAO) {
        this.bankDAO = bankDAO;
        this.sessionUser = null;
    }

    public Account openAccount(Account account){
        return null;
    }

    public double deposit(Account account, double amount){

        return 0;
    }

    public double withdraw(Account account, double amount){

        return 0;
    }

    public  Account[] viewBalances(){

        return null;
    }
}
