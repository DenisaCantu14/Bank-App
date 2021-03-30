package classes.accounts;

import classes.Client;
import classes.Transaction;

import java.util.ArrayList;
import java.util.Objects;

public class AccountStatement {
    private Account account;
    private Client client;
    private ArrayList<Transaction> transactions;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        String afisare = "Account statement" + '\n' +
                         "Account: " + account.toString() + '\n' +
                         "Client: " + client.toString() + '\n';

        for (int i = 0; i <= transactions.size(); i++) {
            afisare += transactions.get(i).toString();
        }
        return afisare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatement that = (AccountStatement) o;
        return Objects.equals(account, that.account) && Objects.equals(client, that.client) && Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, client, transactions);
    }
}
