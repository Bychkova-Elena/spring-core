package console;

import model.User;
import org.springframework.stereotype.Component;
import service.AccountService;
import service.UserService;

import javax.naming.NameAlreadyBoundException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class ConsoleListener {
    private final int ILLEGAL_FIELD = -1;

    private boolean exit;
    private final Scanner scanner;
    private final UserService userService;
    private final AccountService accountService;

    public ConsoleListener(UserService userService, AccountService accountService) {
        this.exit = false;
        this.scanner = new Scanner(System.in);
        this.userService = userService;
        this.accountService = accountService;
    }

    private void userCreate() {
        System.out.println("Enter login:");
        String login = scanner.nextLine().trim();

        if (login.isEmpty()) {
            System.out.println("Login can't be empty!");
            return;
        }

        try {
            userService.createUser(login);
        } catch (NameAlreadyBoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllUsers() {
        Map<Integer, User> users = userService.getUsers();

        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            System.out.println(entry.getValue());
        }

        if (users.isEmpty()) {
            System.out.println("Empty");
        }
    }

    private void accountCreate() {

        System.out.println("Enter user id:");

        int userId = scannerInt();

        try {
            accountService.createAccount(userId);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private void accountDeposit() {

        System.out.println("Enter account id:");

        int accountId = scannerInt();

        System.out.println("Enter amount:");

        double amount = scannerDouble();

        try{
           accountService.depositAccount(accountId, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void accountWithdraw() {
        System.out.println("Enter account id:");

        int accountId = scannerInt();

        System.out.println("Enter amount:");

        double amount = scannerDouble();

        try{
            accountService.withdrawAccount(accountId, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void accountTransfer() {
        System.out.println("Enter source account id:");
        int sourceAccountId = scannerInt();

        System.out.println("Enter target account id:");
        int targetAccountId = scannerInt();

        System.out.println("Enter amount:");
        double amount = scannerDouble();

        try{
            accountService.transferAccount(sourceAccountId, targetAccountId, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void accountClose() {
        System.out.println("Enter account id to close:");
        int accountId = scannerInt();

        try{
            accountService.closeAccount(accountId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void exit() {
        System.out.println("MiniBank stopped.");
        scanner.close();
        exit = true;
    }

    private int scannerInt() {
        int newInt = ILLEGAL_FIELD;

        try {
            newInt = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }

        return newInt;
    }

    private double scannerDouble() {
        double newInt = ILLEGAL_FIELD;

        try {
            newInt = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }

        return newInt;
    }


    public void console() {
        System.out.println("MiniBank started. Type EXIT to stop.");
        System.out.println("Available commands: USER_CREATE, SHOW_ALL_USERS, ACCOUNT_CREATE, ACCOUNT_DEPOSIT, " +
                "ACCOUNT_WITHDRAW, ACCOUNT_TRANSFER, ACCOUNT_CLOSE, EXIT");

        while (!exit) {
            System.out.println();
            System.out.println("Enter command:");
            String command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "USER_CREATE":
                    userCreate();
                    break;

                case "SHOW_ALL_USERS":
                    showAllUsers();
                    break;

                case "ACCOUNT_CREATE":
                    accountCreate();
                    break;

                case "ACCOUNT_DEPOSIT":
                    accountDeposit();
                    break;

                case "ACCOUNT_WITHDRAW":
                    accountWithdraw();
                    break;

                case "ACCOUNT_TRANSFER":
                    accountTransfer();
                    break;

                case "ACCOUNT_CLOSE":
                    accountClose();
                    break;

                case "EXIT":
                    exit();
                    break;

                default:
                    System.out.println("Command not found");
            }
        }
    }
}
