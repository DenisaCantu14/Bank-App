package  classes.transactions;

import classes.MyException;
import classes.accounts.AccountService;
import java.text.ParseException;

public class Transfer extends Transaction {
    private Integer targetAccount;

    public Transfer(Integer sourceAccount, Integer targetAccount, double sold) throws ParseException, MyException {
        super(sourceAccount, sold);
        this.targetAccount = targetAccount;
    }

    public Transfer(Integer ID, String date, Integer sourceAccount, Integer targetAccount, double sold) throws ParseException, MyException {
        super(ID, date, sourceAccount, sold);
        this.targetAccount = targetAccount;
    }

    public Integer getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Integer targetAccount) {
        this.targetAccount = targetAccount;
    }

    @Override
    public String toString() {
        return
                        "ID:" + ID + '\n' +
                        "Date :" + date.getTime() + "\n" +
                        "SOURCE ACCOUNT: " + AccountService.getAccountById(sourceAccount).getIBAN() + '\n' +
                                "TARGET ACCOUNT: " + AccountService.getAccountById(targetAccount).getIBAN() + '\n' +
                                "Sold: " + sold + "RON\n";
    }
}
