package classes;

import classes.accounts.Account;

import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;

public class TransactionService {

    public static void displayTransactions (ArrayList<Transaction> transactions)
    {
        for ( Transaction transaction : transactions)
        {
            System.out.println(transaction.toString());
        }
    }


    public static ArrayList<Transaction> addTransaction (ArrayList<Transaction> transactions, Transaction transaction)
    {
        transactions.add(transaction);
        return transactions;
    }

    public static ArrayList<Transaction> deleteTransaction (ArrayList<Transaction> transactions, Transaction transaction)
    {
        transactions.remove(transaction);
        return transactions;
    }
}
