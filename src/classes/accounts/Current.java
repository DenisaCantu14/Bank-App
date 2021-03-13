package classes.accounts;

import java.util.Date;

public class Current extends Account {
    
    public Current(String IBAN, double balance, Date createDate)
    {
        super(IBAN, balance, createDate);
    }

    @Override
    public void withdraw(double amount) {


    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;

    }


}
