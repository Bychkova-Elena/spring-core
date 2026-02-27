package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String login;
    private List<Account> accountList;

    public User(int id, String login) {
        this.id = id;
        this.login = login;
        this.accountList = new ArrayList<>();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}
