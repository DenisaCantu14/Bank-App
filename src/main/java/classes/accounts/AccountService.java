package classes.accounts;

import classes.MySqlCon;
import  main.java.classes.Audit;
import classes.MyException;
import java.sql.*;
import java.util.ArrayList;

public class AccountService {

    public static ArrayList<Account> accounts = new ArrayList<>();

    public static void getAccounts() throws Exception {
        Audit.write("getAccounts");

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from account, current where idaccount = idcurrent ");
        while (resultSet.next()){
            Account account = new Current(resultSet.getInt("idaccount"),
                    resultSet.getString("iban"),
                    resultSet.getDouble("balance"),
                    resultSet.getString("createDate"),
                    resultSet.getInt("idclient"));
            accounts.add(account);
        }

        resultSet = statement.executeQuery("select * " +
                                               "from account, deposit " +
                                               "where idaccount = iddeposit");
        while (resultSet.next()){
            Account account = new Deposit(resultSet.getInt("idaccount"),
                    resultSet.getString("iban"),
                    resultSet.getDouble("balance"),
                    resultSet.getInt("idclient"),
                    resultSet.getString("createDate"),
                    resultSet.getInt("period"),
                    resultSet.getDouble("gain"));
            accounts.add(account);
        }
    }
    public static void displayAccounts ()
    {
        Audit.write("DisplayAccounts");
        System.out.println("List of accounts: \n");
        for ( Account account : accounts)
        {
            System.out.println(account.toString());
        }
    }


    public static Account getAccountByClientId (Integer id)
    {
        for ( Account account : accounts)
        {
            if ( account.getIdClient().equals(id) ) {
                return account;
            }
        }
        return null;
    }
    public static Account getAccountById (int id)
    {
        for ( Account account : accounts)
        {
            if ( account.getID() == id) {
                return account;
            }
        }
        return  null;
    }
    public static Account getAccountByIban(String IBAN)
    {
        for ( Account account : accounts)
        {
            if ( account.getIBAN().equals(IBAN)) {
                return account;
            }
        }
        return  null;
    }

    public static void withdraw(double amount, Integer ID) throws MyException, SQLException {
        for ( Account account : accounts)
        {
            if ( account.getID()==ID ) {
                System.out.println(account.getBalance());
                if(amount > account.getBalance())
                {
                    throw new MyException("Insufficient funds");
                }
                ((Current) account).withdraw(amount);
            }
        }
    }
    public static void deposit(double amount, Integer ID) throws MyException, SQLException {
        for ( Account account : accounts)
        {
            if ( account.getID()==ID ) {
                account.deposit(amount);
            }
        }
    }

    public static ArrayList<Account> addAccount (Account account) throws SQLException {
        Audit.write("AddAccount");
        accounts.add(account);

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        PreparedStatement preparedStatement;

        preparedStatement = connection.prepareStatement("INSERT INTO account values (?,?,?,?,?)");
        preparedStatement.setInt(1,   account.getID());
        preparedStatement.setString(2,account.getIBAN());
        preparedStatement.setDouble(3,account.getBalance());
        preparedStatement.setDate(4, (Date) account.getCreateDate().getTime());
        preparedStatement.setInt(5,account.getIdClient());
        preparedStatement.execute();

        if(account instanceof Current)
        {
            preparedStatement = connection.prepareStatement("INSERT INTO current values (?)");
            preparedStatement.setInt(1,   account.getID());
            preparedStatement.execute();
        }
        else
        {
            account = (Deposit) account;
            preparedStatement = connection.prepareStatement("INSERT INTO deposit values (?, ?, ?)");
            preparedStatement.setInt(1,   account.getID());
            preparedStatement.setInt(1,   ((Deposit) account).getPeriod());
            preparedStatement.setDouble(1, ((Deposit) account).getGain());
            preparedStatement.execute();
        }


        return accounts;
    }
    public static void updateAccount( Account account) throws SQLException {

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE banckingapp.account SET iban = ?, balance = ?, createdate = ?, idclient = ? WHERE (idaccount = ?);");
        preparedStatement.setString(1,account.getIBAN());
        preparedStatement.setDouble(2,account.getBalance());
        preparedStatement.setDate(3, (Date) account.getCreateDate().getTime());
        preparedStatement.setInt(4,account.getIdClient());
        preparedStatement.setInt(5,account.getID());
        preparedStatement.execute();

        if(account instanceof Deposit)
        { preparedStatement = connection.prepareStatement("UPDATE banckingapp.current SET period = ?, gain = ? WHERE (iddeposit = ?);");
            preparedStatement.setInt(1, ((Deposit) account).getPeriod());
            preparedStatement.setDouble(2,((Deposit) account).getGain());
            preparedStatement.setInt(3,account.getID());
            preparedStatement.execute();
        }
    }
    public static ArrayList<Account> deleteAccount (Account account)
    {
        Audit.write("DeleteAccount");
        accounts.remove(account);
        return accounts;
    }

}
