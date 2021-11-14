package com.revature.quizzard.models;

public class BankTransaction {

    private String bank_transaction_id;
    private String bank_account_id;
    private double amount;
    private String date_added;

    private AppUser trader;
    private BankAccount bankAccountTarget;

    public BankTransaction() {
    }

    public BankTransaction(double amount, AppUser trader, BankAccount bankAccount_Target) {
        this.amount = amount;
        this.trader = trader;
        this.bankAccountTarget = bankAccount_Target;
    }

    public BankTransaction(String bank_transaction_id, String bank_account_id, double amount, AppUser trader) {
        this.bank_transaction_id = bank_transaction_id;
        this.bank_account_id = bank_account_id;
        this.amount = amount;
        this.trader = trader;
    }

    public String getBank_transaction_id() {
        return bank_transaction_id;
    }

    public void setBank_transaction_id(String bank_transaction_id) {
        this.bank_transaction_id = bank_transaction_id;
    }

    public String getBank_account_id() {
        return bank_account_id;
    }

    public void setBank_account_id(String bank_account_id) {
        this.bank_account_id = bank_account_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AppUser getTrader() {
        return trader;
    }

    public void setTrader(AppUser trader) {
        this.trader = trader;
    }

    public BankAccount getBankAccountTarget() {
        return bankAccountTarget;
    }

    public void setBankAccount(BankAccount bankAccountTo) {
        this.bankAccountTarget = bankAccountTo;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "bank_transaction_id='" + bank_transaction_id + '\'' +
                ", bank_account_id='" + bank_account_id + '\'' +
                ", amount=" + amount +
                ", trader=" + trader +
                '}';
    }
}
