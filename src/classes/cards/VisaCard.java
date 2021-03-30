package classes.cards;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class VisaCard
{
    private long cardNumber;
    private Calendar createDate;
    private Calendar expirationDate;
    private String pin;
    private String CVV2;

    public VisaCard(long cardNumber, String pin, String CVV2)
    {
        this.cardNumber = cardNumber;
        this.createDate = Calendar.getInstance();
        this.expirationDate =  Calendar.getInstance();
        expirationDate.add(Calendar.YEAR, 20);
        this.pin = pin;
        this.CVV2 = CVV2;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCreateDate() {
        return createDate.getTime();
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate.getTime();
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
