package classes.transactions;

import classes.MyException;
import classes.accounts.AccountService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Transaction {

    protected static int nrTransactions = 0;
    protected int ID;
    protected Calendar date;
    protected Integer sourceAccount;
    protected double sold;

    public Transaction(Integer sourceAccount, double sold)  {
        this.ID = ++nrTransactions;
        this.date = Calendar.getInstance();
        this.sourceAccount = sourceAccount;
        this.sold = sold;
    }

    public Transaction(Integer ID, String  date, Integer sourceAccount, double sold) throws ParseException {
        this.ID = ID;
        nrTransactions++;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date d = sdf.parse(date);
        this.date = sdf.getCalendar();
        this.sourceAccount = sourceAccount;
        this.sold = sold;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Integer getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Integer sourceAccount) {
        this.sourceAccount = sourceAccount;
    }


    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return
                "ID: " + ID + '\n' +
                "Date :" + date.getTime() + "\n" +
                "SOURCE ACCOUNT: " + AccountService.getAccountById(sourceAccount).getIBAN() + '\n' +
                "Sold: " + sold + "RON\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return ID == that.ID && Double.compare(that.sold, sold) == 0 && Objects.equals(date, that.date) && Objects.equals(sourceAccount, that.sourceAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, date, sourceAccount, sold);
    }

}
