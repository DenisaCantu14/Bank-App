package classes.accounts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

abstract public class Account {

    private static int nrAccounts = 0;
    protected int ID;
    protected String IBAN;
    protected double balance;
    protected Calendar createDate;

    public Account(double balance)
    {
        this.ID = nrAccounts++;
        this.IBAN = "R012" + ID;
        this.balance = balance;
        this.createDate = Calendar.getInstance();
    }

    public Account(int ID, String IBAN, double balance, String  createDate) throws ParseException {
        this.ID = ID;
        nrAccounts ++;
        this.IBAN = IBAN;
        this.balance = balance;

        this.createDate = new GregorianCalendar();
        Date thedate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(createDate);
        this.createDate.setTime(thedate);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate.getTime();
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Account" + '\n' +
                "ID" + ID + '\n' +
                "IBAN: " + IBAN + '\n' +
                "Balance: " + balance + '\n' +
                "Create Date: " + createDate.getTime() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return ID == account.ID && Double.compare(account.balance, balance) == 0 && Objects.equals(IBAN, account.IBAN) && Objects.equals(createDate, account.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, IBAN, balance, createDate);
    }

    abstract public void withdraw(double amount);
    abstract public void deposit(double amount);

}
