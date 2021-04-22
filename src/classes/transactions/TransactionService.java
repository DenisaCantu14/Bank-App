package classes.transactions;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionService {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void getTransactions() throws Exception {
        {
            String line = "";
            String splitBy = ",";
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/files/Transactions.csv"));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(splitBy);

                    int id = Integer.parseInt(data[0]);
                    String date = data[1];
                    double sold = Double.parseDouble(data[2]);
                    int sourceId = Integer.parseInt(data[3]);
                    if(data.length == 5)
                    {
                        int tragetId = Integer.parseInt(data[4]);
                        Transaction t = new Transfer(id, date, sourceId, tragetId, sold);
                        transactions.add(t);
                    }
                    else
                    {
                        Transaction t = new Transaction(id, date, sourceId, sold);
                        transactions.add(t);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void write()
    {
        String data = "";
        try (PrintWriter writer = new PrintWriter(new File("src/files/Transactions.csv")))
        {
            for (Transaction transaction : transactions) {

                data += String.valueOf(transaction.getID()) + ',' +
                        transaction.getDate().getTime() + ',' +
                        transaction.getSold() + ',' +
                        transaction.getSourceAccount();
                if(transaction instanceof Transfer)
                {
                    data += ',' + ((Transfer) transaction).getTargetAccount();
                }
                data += '\n';
            }
            writer.write(data);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void displayTransactions ()
    {
        System.out.println("List of transactions: \n");
        for ( Transaction transaction : transactions)
        {
            System.out.println(transaction.toString());
        }
    }

    public static Transaction getTransactionById(int id)
    {
        for ( Transaction transaction : transactions)
        {
            if ( transaction.getID() == id) {
                return transaction;
            }
        }
        return null;
    }

    public static ArrayList<Integer> getTransactionsByIdAccount(int id, Calendar startDate)
    {
        ArrayList<Integer> accountTransactions = new ArrayList<>();

        for ( Transaction transaction : transactions)
        {

            if ( (transaction.getSourceAccount() == id) && transaction.getDate().compareTo(startDate) > 0)  {
                accountTransactions.add(transaction.getID());
            }
        }
        return accountTransactions;
    }

    public static ArrayList<Transaction> addTransaction (Transaction transaction)
    {
        transactions.add(transaction);
        write();
        return transactions;
    }

    public static ArrayList<Transaction> deleteTransaction (Transaction transaction)
    {
        transactions.remove(transaction);
        write();
        return transactions;
    }
}
