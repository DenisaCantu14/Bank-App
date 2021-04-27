package classes.accounts;

import classes.Audit;
import classes.MyException;

import java.io.*;
import java.util.ArrayList;

public class AccountService {

    public static ArrayList<Account> accounts = new ArrayList<>();

    public static void getAccounts() throws Exception {
        {
            String line = "";
            String splitBy = ",";
            Audit.write("GetAccounts");
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/files/CurrentAccounts.csv"));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(splitBy);
                    Integer id = Integer.parseInt(data[0]);
                    String IBAN = data[1];
                    Double balance = Double.parseDouble(data[2]);
                    Integer idOwner = Integer.parseInt(data[3]);
                    String date = data[4];
                    Account a = new Current(id, IBAN, balance, date, idOwner);
                    accounts.add(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader("src/files/DepositAccounts.csv"));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(splitBy);
                    Integer id = Integer.parseInt(data[0]);
                    String IBAN = data[1];
                    Double balance = Double.parseDouble(data[2]);
                    Integer idOwner = Integer.parseInt(data[3]);
                    String date = data[4];
                    Integer period = Integer.parseInt(data[5]);
                    Double gain = Double.parseDouble(data[6]);
                    Account a = new Deposit(id, IBAN, balance, idOwner, date, period, gain);
                    accounts.add(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

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

    public static void write()
    {
        String data = "";
        Audit.write("");
        try (PrintWriter writer = new PrintWriter(new File("src/files/CurrentAccounts.csv")))
        {
            for (Account account : accounts) {
                if (account instanceof Current) {
                    data += String.valueOf(account.getID()) + ',' +
                            account.getIBAN() + ',' +
                            account.getBalance() + ',' +
                            account.getIdClient() + ',' +
                            account.getCreateDate().getTime().toString() + '\n';
                }
            }
            writer.write(data);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

         data = "";
         try (PrintWriter writer = new PrintWriter(new File("src/files/DepositAccounts.csv"))) {
             for (Account account : accounts) {
                 if (account instanceof Deposit) {
                     data = String.valueOf(account.getID()) + ',' +
                             account.getIBAN() + ',' +
                             account.getBalance() + ',' +
                             account.getIdClient() + ',' +
                             account.getCreateDate().getTime().toString() + ',' +
                             ((Deposit) account).getPeriod() + ',' +
                             ((Deposit) account).getGain() + '\n';

                     writer.write(data);
                 }
             }
         }
         catch (FileNotFoundException e)
         {
             System.out.println(e.getMessage());
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

    public static void withdraw(double amount, Integer ID) throws MyException {
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
        write();
    }
    public static void deposit(double amount, Integer ID) throws MyException {
        for ( Account account : accounts)
        {
            if ( account.getID()==ID ) {
                account.deposit(amount);
            }
        }
        write();
    }

    public static ArrayList<Account> addAccount (Account account)
    {
        Audit.write("AddAccount");
        accounts.add(account);
        write();
        return accounts;
    }

    public static ArrayList<Account> deleteAccount (Account account)
    {
        Audit.write("DeleteAccount");
        accounts.remove(account);
        write();
        return accounts;
    }

}
