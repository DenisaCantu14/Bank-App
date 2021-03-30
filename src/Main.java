import classes.Client;
import classes.ClientService;
import classes.Transaction;
import classes.TransactionService;
import classes.accounts.*;
import classes.cards.VisaCard;

import java.util.ArrayList;
import java.util.Calendar;


public class Main {

    public static void main(String[] args) {

        Client c1 = new Client("Elena", "Popescu", "elena@yahoo.com", "Str Florilor nr4",0123455665 , 012233333 );
        Client c2 = new Client("Mihai", "Ionescu", "mihai@yahoo.com", "Str Eroilor nr4",0123453235 , 0222233333 );
        Client c3 = new Client("Monica", "Petre", "monica@yahoo.com", "Str Mihai Viteazul nr4",0123355665 , 0555533 );
        Client c4 = new Client("Alex", "Olteanu", "alex@yahoo.com", "Str Lalelelor nr4",0165645665 , 77777777 );

        Client [] clients = new Client[0];
        clients = ClientService.addClient(clients, c1);
        clients = ClientService.addClient(clients, c2);
        clients = ClientService.addClient(clients, c3);
        clients = ClientService.addClient(clients, c4);
        ClientService.displayClients(clients);
        ClientService.getClient(clients,c1);

        VisaCard v1 = new VisaCard(123457646, "0000", "1234");
        VisaCard v2 = new VisaCard(576467777, "0001", "0000");

        Account a1 = new Current("RO1221212", 12000, v1);
        Account a2 = new Deposit("RP6456466", 14936, 3);
        Account a3 = new Current("RO6343533", 1233, v2);
        Account a4 = new Deposit("RO6536432", 99993, 3);

        ArrayList<Account> accounts = new ArrayList<Account>();

        accounts = AccountService.addAccount(accounts, a1);
        accounts = AccountService.addAccount(accounts, a2);
        accounts = AccountService.addAccount(accounts, a3);
        accounts = AccountService.addAccount(accounts, a4);
        AccountService.getAccount(accounts, a2);
        AccountService.displayAccounts(accounts);
        accounts = AccountService.deleteAccount(accounts, a4);
        System.out.println("After delete: \n");
        AccountService.displayAccounts(accounts);

        Calendar date1 = Calendar.getInstance();

        Transaction t1 = new Transaction(date1, a1, a2, 40.20);
        Transaction t2 = new Transaction(date1, a3, a4, 5500);

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        transactions = TransactionService.addTransaction(transactions, t1);
        transactions = TransactionService.addTransaction(transactions, t2);
        TransactionService.displayTransactions(transactions);
        transactions = TransactionService.deleteTransaction(transactions, t2);
        System.out.println("After delete: \n");
        TransactionService.displayTransactions(transactions);

    }
}
