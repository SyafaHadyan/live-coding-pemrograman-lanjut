import java.util.Scanner;

class Bank {
    private double cash;
    private double minimumBalance;

    public Bank() {
        this.cash = 500d;
        this.minimumBalance = 100d;
    }

    public Bank(double initialAmount, double minimumBalance) {
        this.cash = initialAmount;
        this.minimumBalance = minimumBalance;
    }

    private void handleWithdraw(double amount) {
        if (this.cash - amount > this.minimumBalance) {
            this.cash -= amount;
        } else {
            System.out.println("Withdrawal not allowed. Minimum balance should be maintained.");
        }
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public double getBalance() {
        return this.cash;
    }

    public double getMinimumBalance() {
        return this.minimumBalance;
    }

    public void deposit(int amount) {
        this.cash += (double) amount;
    }

    public void deposit(long amount) {
        this.cash += (double) amount;
    }

    public void deposit(double amount) {
        this.cash += amount;
    }

    public void withdraw(int amount) {
        handleWithdraw((double) amount);
    }

    public void withdraw(long amount) {
        handleWithdraw((double) amount);
    }

    public void withdraw(double amount) {
        handleWithdraw(amount);
    }

    public String toString() {
        return String.format("Current Balance: %.1f", this.cash);
    }
}

public class BankAccount8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        bank.deposit(Double.parseDouble(scanner.nextLine()));
        bank.withdraw(Double.parseDouble(scanner.nextLine()));
        System.out.println(bank);
        scanner.close();
    }
}
