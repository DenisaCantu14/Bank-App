package classes.accounts;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;


public class Current extends Account {

    private Integer IdCard;
    
    public Current(double balance)
    {
        super(balance);
        this.IdCard = -1;
    }
    public Current(int ID, String IBAN, double balance, String createDate, Integer idCard) throws ParseException
    {
        super(ID, IBAN, balance, createDate);
        this.IdCard = idCard;
    }


    public Integer getIdCard() {
        return IdCard;
    }

    public void setIdCard(Integer idCard) {
        IdCard = idCard;
    }

    public String toString() {
        return  "Current" + '\n' +
                "IBAN: " + IBAN + '\n' +
                "Balance: " + balance + '\n' +
                "Created at: " + createDate.getTime() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Current current = (Current) o;
        return Objects.equals(IdCard, current.IdCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), IdCard);
    }

    @Override
    public void withdraw(double amount) {
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;

    }




}
