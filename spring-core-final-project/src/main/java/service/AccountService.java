package service;

import config.AccountProperties;
import model.Account;
import org.springframework.stereotype.Service;
import storage.BankStorage;

import java.util.NoSuchElementException;

@Service
public class AccountService {
    private final BankStorage bankStorage;
    private final AccountProperties accountProperties;

    public AccountService(BankStorage bankStorage, AccountProperties accountProperties) {
        this.bankStorage = bankStorage;
        this.accountProperties = accountProperties;
    }

    public void createAccount(int userId) {
        boolean isUserExist = bankStorage.isUserExist(userId);

        if (isUserExist) {
            int accountId = bankStorage.getNextAccountId();

            Account account = new Account(accountId, userId, accountProperties.defaultAmount);
            bankStorage.addAccount(accountId, account);

            bankStorage.addAccountToUsersAccountList(userId, account);

            System.out.println("Account created: " + account);
        } else {
            throw new NoSuchElementException("This user doesn't exist");
        }
    }

    public void depositAccount(int id, double profit) {

        boolean isAccountExist = bankStorage.isAccountExit(id);

        if (!isAccountExist) {
            throw new NoSuchElementException("This account doesn't exist");
        }

        if (profit <= 0) {
            throw new IllegalArgumentException("profit must be number and greater then 0");
        }

        Account account = bankStorage.getAccountById(id);

        double moneyAmount = account.getMoneyAmount() + profit;
        account.setMoneyAmount(moneyAmount);

        System.out.printf("Deposited %s to account %s. New balance: %s\n", profit, id, account.getMoneyAmount());
    }

    public void withdrawAccount(int id, double withdraw) {

        boolean isAccountExist = bankStorage.isAccountExit(id);

        if (!isAccountExist) {
            throw new NoSuchElementException("This account doesn't exist");
        }

        if (withdraw <= 0) {
            throw new IllegalArgumentException("withdraw must be number and greater then 0");
        }

        Account account = bankStorage.getAccountById(id);

        double moneyAmount = account.getMoneyAmount();

        if (withdraw > moneyAmount) {
            throw new RuntimeException(String.format(
                    "Error: insufficient funds on account id=%s, moneyAmount=%s, attempted withdraw=%s",
                    id, moneyAmount, withdraw));
        }

        double newMoneyAmount = account.getMoneyAmount() - withdraw;
        account.setMoneyAmount(newMoneyAmount);

        System.out.printf("Withdraw %s to account %s. New balance: %s\n", withdraw, id, newMoneyAmount);
    }

    public void transferAccount(int sourceAccountId, int targetAccountId, double amount) {

        if (!bankStorage.isAccountExit(sourceAccountId)) {
            throw new NoSuchElementException("The source account doesn't exist");
        }

        if (!bankStorage.isAccountExit(targetAccountId)) {
            throw new NoSuchElementException("The target account doesn't exist");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be number and greater then 0");
        }

        Account sourceAccount = bankStorage.getAccountById(sourceAccountId);
        Account targetAccount = bankStorage.getAccountById(targetAccountId);

        double sourceAccountMoneyAmount = sourceAccount.getMoneyAmount();
        double targetAccountMoneyAmount = targetAccount.getMoneyAmount();

        if (amount > sourceAccountMoneyAmount) {
            throw new RuntimeException(String.format("Error: insufficient funds on account id=%s, " +
                    "moneyAmount=%s, attempted withdraw=%s", sourceAccountId, sourceAccountMoneyAmount, amount));
        }

        sourceAccount.setMoneyAmount(sourceAccountMoneyAmount - amount);

        boolean isOnePersonsAccount = sourceAccount.getUserId() == targetAccount.getUserId();

        double moneyReceived =
                isOnePersonsAccount ? amount : (amount - (amount * accountProperties.transferCommission));

        double newTargetMoneyAmount = targetAccountMoneyAmount + moneyReceived;

        targetAccount.setMoneyAmount(newTargetMoneyAmount);

        if (isOnePersonsAccount) {
            System.out.printf("Transfer completed from account %s to account %s. Amount: %s (no commission, "
                    + "same user)\n", sourceAccountId, targetAccountId, amount);
        } else {
            double commission = amount - moneyReceived;
            System.out.printf("Transfer completed from account %s to account %s. Amount: %s, commission: %s, "
                            + "recipient received: %s\n", sourceAccountId, targetAccountId, amount,
                    commission, moneyReceived);
        }
    }

    public void closeAccount(int accountId) {

        if (!bankStorage.isAccountExit(accountId)) {
            throw new NoSuchElementException("This account doesn't exist");
        }

        Account account = bankStorage.getAccountById(accountId);
        int userId = account.getUserId();

        var another = bankStorage.getUsersAnotherAccount(userId, accountId);

        if (another.isEmpty()) {
            throw new RuntimeException("User doesn't have another account!");
        }

        Account anotherAccount = another.get();

        double remainingBalance = account.getMoneyAmount();

        anotherAccount.setMoneyAmount(anotherAccount.getMoneyAmount() + remainingBalance);

        bankStorage.removeAccount(userId, accountId);

        System.out.printf("Account %s closed. Remaining balance %s transferred to account %s.\n",
                accountId, remainingBalance, anotherAccount.getId());
    }
}
