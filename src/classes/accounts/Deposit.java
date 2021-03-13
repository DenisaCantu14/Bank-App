package classes.accounts;

import java.util.Date;

public class Deposit extends Account {
    private int period;
    public Deposit(String IBAN, double balance, Date createDate, int period)
    {
        super(IBAN, balance, createDate);
        this.period = period;
    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public void deposit(double amount) {

    }

}
