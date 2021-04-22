package classes.accounts;

import java.io.*;
import java.util.ArrayList;

public class AccountStatementService {
    public static ArrayList<AccountStatement> statements = new ArrayList<>();


    public static void getAccountStatements() throws Exception {
        {
            String line = "";
            String splitBy = ",";
            try {

                    BufferedReader br = new BufferedReader(new FileReader("src/files/Statements.csv"));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(splitBy);
                    int id = Integer.parseInt(data[0]);
                    int idAccount = Integer.parseInt(data[1]);
                    String startDate = data[2];
                    String createDate = data[3];
                    double balance = Double.parseDouble(data[4]);
                    String [] ids = data[5].split(" ");
                    ArrayList<Integer> idTransactions = new ArrayList<>();

                    {
                        for (String i : ids) {
                            if(i != "")
                                idTransactions.add(Integer.parseInt(i));
                        }
                    }

                    AccountStatement ac = new AccountStatement(id, idAccount, startDate, createDate, balance, idTransactions);
                    statements.add(ac);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void displayStatements()
    {

        System.out.println("List of statements: \n");
        for ( AccountStatement statement : statements)
        {
            System.out.println(statement.toString());
        }
    }
    public static AccountStatement getStatementById (int id)
    {
        for ( AccountStatement statement : statements)
        {
            if ( statement.getID() == id) {
                return statement;
            }
        }
        return null;
    }



    public static ArrayList <AccountStatement> addAccountStatement (AccountStatement statement)
    {
        statements.add(statement);
        write();
        return statements;

    }

    public static void write()
    {
        String data = "";
        try (PrintWriter writer = new PrintWriter(new File("src/files/Statements.csv"))) {
            for (AccountStatement statement : statements) {
                data += String.valueOf(statement.getID()) + ',' +
                        statement.getIdAccount() + ',' +
                        statement.getStartDate().getTime() + ',' +
                        statement.getCreateDate().getTime() + ',' +
                        statement.getBalance() + ',';
                for (Integer id : statement.getIdTransactions()) {
                    data += id.toString() + ' ';
                }
                data += " \n";

            }
            writer.write(data);
        }
            catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }


    public static ArrayList<AccountStatement> deleteAccountStatement (AccountStatement statement)
    {
        statements.remove(statement);
        write();
        return statements;
    }


}
