package classes;

import java.util.Date;

public class Card
{

    private String bankBranding;
    private long cardNumber;
    private String name;
    private Date createDate;
    private Date expirationDate;
    private int pin;
    private String CVV2;

    public Card(String bankBranding, long cardNumber, String name, Date createDate, int pin, String CVV2)
    {
        this.bankBranding = bankBranding;
        this.cardNumber = cardNumber;
        this.name = name;
        this.createDate = createDate;
        this.expirationDate.setYear(createDate.getYear() + 3);
        this.pin = pin;
        this.CVV2 = CVV2;

    }

}
