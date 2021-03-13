package classes.cards;

import classes.Transaction;

import java.util.ArrayList;
import java.util.Date;

public class VisaCard
{

    protected String bankBranding;
    protected long cardNumber;
    protected String name;
    protected Date createDate;
    protected Date expirationDate;
    protected int pin;
    protected String CVV2;
    protected ArrayList<Transaction> transactions;
    protected double limit;

    public VisaCard(String bankBranding, long cardNumber, String name, Date createDate, int pin, String CVV2)
    {
        this.bankBranding = bankBranding;
        this.cardNumber = cardNumber;
        this.name = name;
        this.createDate = createDate;
        this.expirationDate.setYear(createDate.getYear() + 3);
        this.pin = pin;
        this.CVV2 = CVV2;
        this.transactions = new ArrayList<Transaction>();

    }

    public void addTransaction (double price)
    {

    }

    public void withdraw (double sold)
    {

    }

}
