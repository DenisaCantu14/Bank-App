package classes;

import classes.accounts.Account;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Transaction {

    private Calendar date;
    private Account sourceAccount;
    private Account targetAccount;
    private double sold;

    public Transaction(Calendar date, Account sourceAccount, Account targetAccount, double sold) {
        this.date = date;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.sold = sold;
    }

    public Date getDate() {
        return date.getTime();
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "TRANSACTION" + '\n' +
                "Date :" + date.getTime() + "\n\n" +
                "SOURCE ACCOUNT: " + '\n' + sourceAccount.toString() +
                "TARGET ACCOUNT: " + '\n' + targetAccount.toString() + '\n' +
                "Sold: " + sold + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.sold, sold) == 0 && Objects.equals(date, that.date) && Objects.equals(sourceAccount, that.sourceAccount) && Objects.equals(targetAccount, that.targetAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, sourceAccount, targetAccount, sold);
    }
}
