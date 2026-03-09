package model;

import org.springframework.beans.factory.annotation.Value;

public class Account {
    private final int id;
    private final int userId;
    private double moneyAmount;

    public Account(int id, int userId, @Value("${account.default-amount}") double moneyAmount) {
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public int getUserId() {
        return userId;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
