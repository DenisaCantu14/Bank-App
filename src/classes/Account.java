package classes;

public class Account {

    private String IBAN;
    private double balance;
    private String createDate;



    public Account(String IBAN, double balance, String createDate)
    {
        this.IBAN = IBAN;
        this.balance = balance;
        this.createDate = createDate;
    }

    public Account(Account other)
    {
        this.IBAN = other.IBAN;
        this.balance = other.balance;
        this.createDate = other.createDate;
    }



}
