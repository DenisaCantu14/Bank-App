package classes;

import classes.accounts.Account;

import java.util.Date;

public class Transaction {

    private Date date;
    private Account taregetAccount;
    private double sold;


    public Transaction (Date date,Account taregetAccount, double sold) {
        this.date = date;
        this.taregetAccount = taregetAccount;
        this.sold = sold;
    }

}
