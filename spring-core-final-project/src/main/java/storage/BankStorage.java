package storage;

import model.Account;
import model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class BankStorage {
    private final Map<Integer, User> users;
    private final Map<Integer, Account> accounts;

    public BankStorage() {
        users = new HashMap<>();
        accounts = new HashMap<>();
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public boolean isUserExist(int userId) {
        return users.containsKey(userId);
    }

    public boolean isUsersLoginExist(String login) {
        AtomicBoolean isUsersLoginExist = new AtomicBoolean(false);

        users.forEach((key, value) -> {
            isUsersLoginExist.set(value.getLogin().equals(login));
        });

        return isUsersLoginExist.get();
    }

    public int getNextUserId() {
        return users.size() + 1;
    }

    public void addUser(int id, User user) {
        users.put(id, user);
    }

    public void addAccountToUsersAccountList(int userId, Account account) {
        boolean isUserExist = isUserExist(userId);

        if (isUserExist) {
            User user = users.get(userId);

            List<Account> usersAccount = user.getAccountList();
            usersAccount.add(account);
            user.setAccountList(usersAccount);
        } else {
            throw new NoSuchElementException("This user doesn't exist");
        }
    }

    public int getNextAccountId() {
        return accounts.size() + 1;
    }

    public boolean isAccountExit(int id) {
        return accounts.containsKey(id);
    }

    public void addAccount(int id, Account account) {
        accounts.put(id, account);
    }

    public Account getAccountById(int id) {
        return accounts.get(id);
    }

    public Optional<Account> getUsersAnotherAccount(int userId, int accountId) {
        return Optional.ofNullable(users.get(userId))
                .map(User::getAccountList)
                .stream()
                .flatMap(List::stream)
                .filter(acc -> acc.getId() != accountId)
                .findFirst();
    }

    public void removeAccount(int userId, int accountId) {
        accounts.remove(accountId);

        User user = users.get(userId);
        List<Account> usersAccount = user.getAccountList();
        usersAccount.removeIf(account -> account.getId() == accountId);
        user.setAccountList(usersAccount);
    }
}
