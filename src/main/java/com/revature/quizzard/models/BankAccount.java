package com.revature.quizzard.models;

public class BankAccount {

    private String bank_account_id;
    private String accountName;
    private String AccountNumber;
    private boolean isJoinedAccount;
    private String accountType;
    private double balance;
    private AppUser creator;
    private String date_added;

    public BankAccount(String accountName, String accountNumber, boolean isJoinedAccount, String accountType, double balance) {
        this.accountName = accountName;
        AccountNumber = accountNumber;
        this.isJoinedAccount = isJoinedAccount;
        this.accountType = accountType;
        this.balance = balance;
    }

    public BankAccount() {
    }

    public String getBank_account_id() {
        return bank_account_id;
    }

    public void setBank_account_id(String bank_account_id) {
        this.bank_account_id = bank_account_id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public boolean isJoinedAccount() {
        return isJoinedAccount;
    }

    public void setJoinedAccount(boolean joinedAccount) {
        isJoinedAccount = joinedAccount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AppUser getCreator() {
        return creator;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + bank_account_id + '\'' +
                ", accountName='" + accountName + '\'' +
                ", AccountNumber='" + AccountNumber + '\'' +
                ", isJoinedAccount=" + isJoinedAccount +
                ", accountType=" + accountType +
                '}';
    }
}
