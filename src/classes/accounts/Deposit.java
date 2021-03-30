package classes.accounts;


import java.util.Objects;

public class Deposit extends Account {
    private int period;
    public Deposit(String IBAN, double balance, int period)
    {
        super(IBAN, balance);
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
