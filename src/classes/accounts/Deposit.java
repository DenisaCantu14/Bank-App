package classes.accounts;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;

public class Deposit extends Account {

    private int period;
    public Deposit(double balance, int period)
    {
        super(balance);
        this.period = period;
    }

    public Deposit(int ID, String IBAN, double balance, String createDate, int period) throws ParseException {
        super(ID, IBAN, balance, createDate);
        this.period = period;
    }
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return  "Deposit" + '\n' +
                "IBAN: " + IBAN + '\n' +
                "Balance = " + balance + '\n' +
                "Created at: " + createDate.getTime() + '\n' +
                "Period: " + period + " months\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deposit deposit = (Deposit) o;
        return period == deposit.period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), period);
    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public void deposit(double amount) {

    }

}
