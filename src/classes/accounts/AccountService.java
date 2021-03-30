package classes.accounts;

import java.util.ArrayList;

public class AccountService {

    public static void displayAccounts (ArrayList<Account> accounts)
    {
        System.out.println("List of accounts: \n");
        for ( Account account : accounts)
        {
            System.out.println(account.toString());
        }
    }

    public static void getAccount (ArrayList<Account> accounts, Account a)
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

    public static ArrayList<Account> addAccount (ArrayList<Account> accounts, Account account)
    {
        accounts.add(account);
        return accounts;
    }

    public static ArrayList<Account> deleteAccount (ArrayList<Account> accounts, Account account)
    {
        accounts.remove(account);
        return accounts;
    }

}
