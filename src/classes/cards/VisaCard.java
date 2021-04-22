package classes.cards;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class VisaCard
{
    private static int nrCards = 0;
    private int ID;
    private String  cardNumber;
    private Calendar createDate;
    private Calendar expirationDate;
    private String pin;
    private String CVV2;

    public VisaCard(String cardNumber, String pin, String CVV2)
    {
        this.ID = nrCards++;
        this.cardNumber = cardNumber;
        this.createDate = Calendar.getInstance();
        this.expirationDate =  Calendar.getInstance();
        expirationDate.add(Calendar.YEAR, 20);
        this.pin = pin;
        this.CVV2 = CVV2;
    }

    public VisaCard(int ID, String cardNumber, String createDate, String expirationDate, String pin, String CVV2) throws ParseException {
        nrCards++;
        this.ID = ID;
        this.cardNumber = cardNumber;

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date date = sdf.parse(createDate);
        this.createDate = sdf.getCalendar();

        sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        date = sdf.parse(expirationDate);
        this.expirationDate = sdf.getCalendar();

        this.pin = pin;
        this.CVV2 = CVV2;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCVV2() {
        return CVV2;
    }

    public void setCVV2(String CVV2) {
        this.CVV2 = CVV2;
    }

    @Override
    public String toString() {
        return  "Card number: " + cardNumber + '\n' +
                "Created at: " + createDate.getTime() + '\n' +
                "Expire at: " + expirationDate.getTime() + '\n' +
                "Pin: " + pin + '\n' +
                "CVV2: " + CVV2 + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisaCard visaCard = (VisaCard) o;
        return cardNumber == visaCard.cardNumber && pin == visaCard.pin && Objects.equals(createDate, visaCard.createDate) && Objects.equals(expirationDate, visaCard.expirationDate) && Objects.equals(CVV2, visaCard.CVV2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, createDate, expirationDate, pin, CVV2);
    }
}
