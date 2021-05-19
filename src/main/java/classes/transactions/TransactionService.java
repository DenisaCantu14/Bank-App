package  classes.transactions;

import classes.MySqlCon;
import  main.java.classes.Audit;
import java.sql.*;
import java.util.ArrayList;

public class TransactionService {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void getTransactions() throws Exception
        {
            Audit.write("getTransactions");

            MySqlCon mySqlCon = new MySqlCon();
            Connection connection = mySqlCon.Connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *" +
                                                             "from transaction, transfer " +
                                                             "where idtransaction != idtransfer;");
            while (resultSet.next()){
                Transaction transaction = new Transaction (resultSet.getInt("idtransaction"),
                        resultSet.getString("date"),
                        resultSet.getInt("sourceaccount"),
                        resultSet.getDouble("sold"));
                transactions.add(transaction);
            }


            resultSet = statement.executeQuery("select *" +
                                                    "from transaction, transfer " +
                                                    "where idtransaction = idtransfer;");
            while (resultSet.next()){
                Transaction transaction = new Transfer (resultSet.getInt("idtransaction"),
                        resultSet.getString("date"),
                        resultSet.getInt("sourceaccount"),
                        resultSet.getInt("targetaccount"),
                        resultSet.getDouble("sold"));
                transactions.add(transaction);
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

    public static ArrayList<Integer> getTransactionsByIdAccount(int id, String startDate)
    {
        ArrayList<Integer> accountTransactions = new ArrayList<>();

        for ( Transaction transaction : transactions)
        {

            if ( (transaction.getSourceAccount() == id) && transaction.getDate().toString().compareTo(startDate) > 0)  {
                accountTransactions.add(transaction.getID());
            }
        }
        return accountTransactions;
    }

    public static ArrayList<Transaction> addTransaction (Transaction transaction) throws SQLException {
        Audit.write("addTransaction");
        transactions.add(transaction);
        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        PreparedStatement preparedStatement = null;

        preparedStatement = connection.prepareStatement("INSERT INTO transaction values (?,?,?,?)");
        preparedStatement.setInt(1,   transaction.getID());
        preparedStatement.setDate(2, (Date) transaction.getDate().getTime());
        preparedStatement.setInt(3,transaction.getSourceAccount());
        preparedStatement.setDouble(4,transaction.getSold());
        preparedStatement.execute();

        if(transaction instanceof Transfer)
        {
            preparedStatement = connection.prepareStatement("INSERT INTO transfer values (?,?)");
            preparedStatement.setInt(1,   transaction.getID());
            preparedStatement.setInt(2,((Transfer) transaction).getTargetAccount());
            preparedStatement.execute();
        }
        return transactions;
    }


    public static ArrayList<Transaction> deleteTransaction (Transaction transaction)
    {
        Audit.write("deleteTransaction");

        transactions.remove(transaction);
        return transactions;
    }
}
