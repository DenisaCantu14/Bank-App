package classes.accounts;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

abstract public class Account {

    protected String IBAN;
    protected double balance;
    protected Calendar createDate;

    public Account(String IBAN, double balance)
    {
        this.IBAN = IBAN;
        this.balance = balance;
        this.createDate = Calendar.getInstance();
    }


    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate.getTime();
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Account" + '\n' +
                "IBAN: " + IBAN + '\n' +
                "Balance: " + balance + '\n' +
                "Create Date: " + createDate.getTime() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equals(IBAN, account.IBAN) && Objects.equals(createDate, account.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN, balance, createDate);
    }

    abstract public void withdraw(double amount);
    abstract public void deposit(double amount);

}
