package classes;

import classes.accounts.Account;

import java.util.ArrayList;

public class Client {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private long phoneNumber;
    private long personalCodeNumber;
    private ArrayList<Account> accounts ;

    public Client(String firstName, String lastName, String email, String address, long phoneNumber, long personalCodeNumber, Account account)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personalCodeNumber = personalCodeNumber;
        this.accounts = new ArrayList<Account>();
        this.accounts.add(account);
    }

}
