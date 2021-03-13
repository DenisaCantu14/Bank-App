package classes.cards;

import java.util.Date;

public class MasterCard extends VisaCard{
    private double withdrawalCommission;

    public MasterCard(String bankBranding, long cardNumber, String name, Date createDate, int pin, String CVV2, double commission)
    {
        super(bankBranding, cardNumber, name, createDate, pin, CVV2);
        this.withdrawalCommission = commission;
    }
    @Override
    public void withdraw (double sold)
    {

    }
}
