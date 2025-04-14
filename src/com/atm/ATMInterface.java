package com.atm;

import java.util.Scanner;

public class ATMInterface {

    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance >= 0) {
                this.balance = initialBalance;
            } else {
                this.balance = 0;
                System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            }
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
            }
        }
    }

    static class ATM {
        private BankAccount bankAccount;

        public ATM(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }

        public void displayMenu() {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- ATM Menu ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        }

        private void checkBalance() {
            System.out.println("Your current balance is: " + bankAccount.getBalance());
        }

        private void deposit() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            if (amount > 0) {
                bankAccount.deposit(amount);
                System.out.println("Deposit successful. Your new balance is: " + bankAccount.getBalance());
            } else {
                System.out.println("Invalid amount. Deposit failed.");
            }
        }

        private void withdraw() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (amount > 0 && bankAccount.getBalance() >= amount) {
                bankAccount.withdraw(amount);
                System.out.println("Withdrawal successful. Your new balance is: " + bankAccount.getBalance());
            } else if (amount > 0) {
                System.out.println("Insufficient funds. Withdrawal failed.");
            } else {
                System.out.println("Invalid amount. Withdrawal failed.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial balance for the account: ");
        double initialBalance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        atm.displayMenu();
    }
}