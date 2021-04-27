package classes.accounts;

import classes.MyException;
import classes.transactions.Transaction;
import classes.transactions.TransactionService;

import java.text.ParseException;

public class Current extends Account {


    
    public Current(double balance, Integer idClient)
    {
        super(balance, idClient);
    }
    public Current(int ID, String IBAN, double balance, String createDate, Integer idClient) throws ParseException
    {
        super(ID, IBAN, balance, createDate, idClient);
    }

    public String toString() {
        return
                "Current" + '\n' +
                "ID Account: " + ID + '\n' +
                "IBAN: " + IBAN + '\n' +
                "Balance: " + balance + '\n' +
                "Created at: " + createDate.getTime() +
                "Client Id: " + IdClient + '\n';
    }

    public void withdraw(double amount)  {
        this.balance -= amount;
        Transaction t = new Transaction(ID, amount);
        TransactionService.addTransaction(t);
    }
    @Override
    public void deposit(double amount)  {
        this.balance += amount;
        Transaction t = new Transaction(ID, amount);
        TransactionService.addTransaction(t);

    }
}
