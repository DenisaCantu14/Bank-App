package classes.accounts;

import classes.Client;
import classes.cards.VisaCard;

import java.util.Objects;


public class Current extends Account {
    private VisaCard card;
    
    public Current(String IBAN, double balance, VisaCard card)
    {
        super(IBAN, balance);
        this.card = card;
    }

    public String toString() {
        return  "Current" + '\n' +
                "IBAN: " + IBAN + '\n' +
                "Balance: " + balance + '\n' +
                "Created at: " + createDate.getTime() + '\n' +
                "CARD: " + '\n' + card.toString() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Current current = (Current) o;
        return Objects.equals(card, current.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), card);
    }

    @Override
    public void withdraw(double amount) {
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;

    }




}
