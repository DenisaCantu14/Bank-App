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
    private Integer idAccount;
    private Random random = new Random(System.currentTimeMillis());

    public VisaCard(String pin, Integer idAccount)
    {
        this.ID = ++nrCards;
        this.cardNumber = createCardNumber(12);
        this.createDate = Calendar.getInstance();
        this.expirationDate =  Calendar.getInstance();
        expirationDate.add(Calendar.YEAR, 3);
        this.pin = pin;
        this.CVV2 = createCardNumber(4);
        this.idAccount = idAccount;

    }

    public VisaCard(int ID, String cardNumber, String createDate, String expirationDate, String pin, String CVV2, Integer idAccount) throws ParseException {
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
        this.idAccount = idAccount;
    }

    public String createCardNumber(int n)
    {
        StringBuilder builder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int digit = this.random.nextInt(10);
            builder.append(digit);
        }
        return builder.toString();
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

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return  "ID: " + ID + '\n' +
                "Card number: " + cardNumber + '\n' +
                "Created at: " + createDate.getTime() + '\n' +
                "Expire at: " + expirationDate.getTime() + '\n' +
                "Pin: " + pin + '\n' +
                "CVV2: " + CVV2 + '\n' +
                "ID account: " + idAccount + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisaCard card = (VisaCard) o;
        return ID == card.ID && Objects.equals(cardNumber, card.cardNumber) && Objects.equals(createDate, card.createDate) && Objects.equals(expirationDate, card.expirationDate) && Objects.equals(pin, card.pin) && Objects.equals(CVV2, card.CVV2) && Objects.equals(idAccount, card.idAccount) && Objects.equals(random, card.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, cardNumber, createDate, expirationDate, pin, CVV2, idAccount, random);
    }
}
