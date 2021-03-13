package classes;

import classes.accounts.Account;

import java.util.ArrayList;

public class Bank {
    private static Bank instance;

    private String name;
    private String address;
    private int code;
    private ArrayList<Account> accounts ;
    private ArrayList<Client> clients;

    private Bank(String name, String address, int code)
    {
        this.name = name;
        this.address = address;
        this.code = code;
        this.accounts = new ArrayList<Account>();
    }

    public Bank getInstance(String name, String address, int code)
    {
        if(instance == null)
            {
                instance = new Bank(name, address, code);
            }
        return instance;

    }

//    public void addAccount (String IBAN, double balance, Date createDate)
//    {
//       Account newAccount = new Account(IBAN, balance, createDate) ;
//       this.accounts.add(newAccount);
//    }



}

