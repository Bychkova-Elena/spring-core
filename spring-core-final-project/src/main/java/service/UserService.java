package service;

import config.AccountProperties;
import model.Account;
import model.User;
import org.springframework.stereotype.Service;
import storage.BankStorage;

import javax.naming.NameAlreadyBoundException;
import java.util.Map;

@Service
public class UserService {
    private final BankStorage bankStorage;
    private final AccountProperties accountProperties;

    public UserService(BankStorage bankStorage, AccountProperties accountProperties) {
        this.bankStorage = bankStorage;
        this.accountProperties = accountProperties;
    }

    public void createUser(String login) throws NameAlreadyBoundException {

        if (bankStorage.isUsersLoginExist(login)) {
            throw new NameAlreadyBoundException("This login already exist");
        }

        int userId = bankStorage.getNextUserId();
        User user = new User(userId, login);
        bankStorage.addUser(userId, user);

        int accountId = bankStorage.getNextAccountId();
        Account account = new Account(accountId, userId, accountProperties.defaultAmount);

        bankStorage.addAccountToUsersAccountList(userId, account);
        bankStorage.addAccount(accountId, account);

        System.out.println("User created: " + user);
    }

    public Map<Integer, User> getUsers() {
        return bankStorage.getUsers();
    }
}
