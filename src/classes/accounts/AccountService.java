package classes.accounts;

import java.io.*;
import java.util.ArrayList;

public class AccountService {

    public static ArrayList<Account> accounts = new ArrayList<>();

    public static void getAccounts() throws Exception {
        {
            String line = "";
            String splitBy = ",";
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/files/CurrentAccounts.csv"));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(splitBy);
                    Integer id = Integer.parseInt(data[0]);
                    String IBAN = data[1];
                    Double balance = Double.parseDouble(data[2]);

                    String date = data[3];
                    Integer IdCard = Integer.parseInt(data[4]);
                    Account a = new Current(id, IBAN, balance, date, IdCard);
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
                    String date = data[3];
                    Integer period = Integer.parseInt(data[4]);
                    Account a = new Deposit(id, IBAN, balance, date, period);
                    accounts.add(a);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void displayAccounts ()
    {
        System.out.println("List of accounts: \n");
        for ( Account account : accounts)
        {
            System.out.println(account.toString());
        }
    }
    public static void write()
    {
        for (Account account : accounts)
        {
            if(account instanceof Current ){
                try (PrintWriter writer = new PrintWriter(new File("src/files/CurrentAccounts.csv"))) {

                    String data = String.valueOf(account.getID()) + ',' +
                            account.getIBAN() + ',' +
                            account.getBalance() + ',' +
                            account.getCreateDate().toString() + ',' +
                            ((Current) account).getIdCard() + '\n';

                    writer.write(data);
                    System.out.println("done!");

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

            else {
                try (PrintWriter writer = new PrintWriter(new File("src/files/DepositAccounts.csv"))) {

                    String data = String.valueOf(account.getID()) + ',' +
                            account.getIBAN() + ',' +
                            account.getBalance() + ',' +
                            account.getCreateDate().toString() + ',' +
                            ((Deposit) account).getPeriod() + '\n';

                    writer.write(data);
                    System.out.println("done!");

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public static void getAccount (Account a)
    {
        for ( Account account : accounts)
        {
            if ( account.equals(a) ) {
                System.out.println("The account is: ");
                System.out.println(account.toString());
                break;
            }
        }
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

    public static ArrayList<Account> addAccount (Account account)
    {
        accounts.add(account);
        write();
        return accounts;
    }

    public static ArrayList<Account> deleteAccount (Account account)
    {
        accounts.remove(account);
        write();
        return accounts;
    }

}
