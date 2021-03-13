package classes.accounts;

import java.util.Date;

abstract public class Account {

    protected String IBAN;
    protected double balance;
    protected Date createDate;

    public Account(String IBAN, double balance, Date createDate)
    {
        this.IBAN = IBAN;
        this.balance = balance;
        this.createDate = createDate;
    }

    public Account(Account other)
    {
        this.IBAN = other.IBAN;
        this.balance = other.balance;
        this.createDate = other.createDate;
    }

    public double getBalance()
    {
        return this.balance;
    }
    abstract public void withdraw(double amount);
    abstract public void deposit(double amount);


}
