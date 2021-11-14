package com.revature.quizzard.daos;

import com.revature.quizzard.models.AppUser;
import com.revature.quizzard.models.BankAccount;
import com.revature.quizzard.models.BankTransaction;
import com.revature.quizzard.util.collections.LinkedList;
import com.revature.quizzard.util.collections.List;
import com.revature.quizzard.util.datasource.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BankDAO implements CrudDAO<BankAccount> {

    @Override
    public BankAccount save(BankAccount bankAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            bankAccount.setBank_account_id(UUID.randomUUID().toString());
            String sql = "insert into bank_account (bank_account_id, account_name, account_type, creator_id) values (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bankAccount.getBank_account_id());
            pstmt.setString(2, bankAccount.getAccountName());
            pstmt.setString(3, bankAccount.getAccountType());
            pstmt.setString(4, bankAccount.getCreator().getId());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return bankAccount;
            }
        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
        }
        return null;
    }

    public BankTransaction transact_bank(BankTransaction bankTransaction) {
        save_transaction(bankTransaction);
        update_balance(bankTransaction);

        return null;
    }

    public BankTransaction save_transaction(BankTransaction bankTransaction) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            bankTransaction.setBank_transaction_id(UUID.randomUUID().toString());
            String sql = "insert into bank_transaction (bank_transaction_id, bank_account_id, trader_id, amount) values (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bankTransaction.getBank_transaction_id());
            pstmt.setString(2, bankTransaction.getBank_account_id());
            pstmt.setString(3, bankTransaction.getTrader().getId());
            pstmt.setDouble(4, bankTransaction.getAmount());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return bankTransaction;
            }
        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
        }
        return null;
    }

    public BankTransaction update_balance(BankTransaction bankTransaction) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "update bank_account set balance = balance + ? where bank_account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, bankTransaction.getAmount());
            pstmt.setString(2, bankTransaction.getBank_account_id());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return bankTransaction;
            }
        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
        }
        return null;
    }

    public List<BankAccount> findBankAccountsByCreatorId(String creatorId) {
        List<BankAccount> bank_accounts = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * " +
                    "from bank_account b " +
                    "join app_users u " +
                    "on b.creator_id = u.user_id " +
                    "where u.user_id = ? " +
                    " order by b.date_added asc";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, creatorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BankAccount bankAccount = new BankAccount();
                AppUser bankAccountCreator = new AppUser();
                bankAccount.setBank_account_id(rs.getString("bank_account_id"));
                bankAccount.setAccountName(rs.getString("account_name"));
                bankAccount.setAccountType(rs.getString("account_type"));
                bankAccount.setBalance(rs.getDouble("balance"));
                bankAccount.setCreator(bankAccountCreator);
                bank_accounts.add(bankAccount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank_accounts;
    }

    public List<BankTransaction> findTransactionsByUserAccountId(String creatorId) {
        List<BankTransaction> bank_transactions = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select *, t.date_added t_date_added " +
                    "from bank_transaction t " +
                    "join bank_account a " +
                    "on t.bank_account_id = a.bank_account_id " +
                    "join app_users u " +
                    "on t.trader_id = u.user_id " +
                    "where a.bank_account_id = ? " +
                    " order by t.date_added asc";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, creatorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BankAccount bankAccount = new BankAccount();
                AppUser trader = new AppUser();
                BankTransaction bankTransaction = new BankTransaction();

                bankTransaction.setBank_transaction_id(rs.getString("bank_transaction_id"));
                bankTransaction.setBank_account_id(rs.getString("bank_account_id"));
                bankTransaction.setDate_added(rs.getString("t_date_added"));
                bankTransaction.setAmount(rs.getDouble("amount"));

                bankAccount.setAccountName(rs.getString("account_name"));
                bankAccount.setAccountType(rs.getString("account_type"));
                bankAccount.setBalance(rs.getDouble("balance"));
                bankTransaction.setBankAccount(bankAccount);

                trader.setFirstName(rs.getString("first_name"));
                trader.setLastName(rs.getString("last_name"));
                bankTransaction.setTrader(trader);

                bank_transactions.add(bankTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank_transactions;
    }

    @Override
    public List<BankAccount> findAll() {
        return null;
    }

    @Override
    public BankAccount findById(String id) {
        return null;
    }

    @Override
    public boolean update(BankAccount updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }

}
