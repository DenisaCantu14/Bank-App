package  classes.accounts;

import  classes.transactions.TransactionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccountStatement {
    private static Integer nrStatements = 0;
    private Integer ID;
    private double balance;
    private Calendar createDate;
    private Integer idAccount;
    private ArrayList<Integer> idTransactions;

    public AccountStatement(Integer idAccount) throws Exception {
        this.ID = ++nrStatements;
        this.idAccount = idAccount;
        this.balance = AccountService.getAccountById(idAccount).getBalance();
        this.createDate = Calendar.getInstance();
        this.idTransactions =  TransactionService.getTransactionsByIdAccount(idAccount);
    }

    public AccountStatement(Integer ID, Integer idAccount, String createDate, double balance, ArrayList<Integer> idTransactions) throws ParseException {
        nrStatements++;
        this.ID = ID;
        this.idAccount = idAccount;
        this.idTransactions = idTransactions;
        this.balance = balance;

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date date = sdf.parse(createDate);
        this.createDate = sdf.getCalendar();


    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public ArrayList<Integer> getIdTransactions() {
        return idTransactions;
    }

    public void setIdTransactions(ArrayList<Integer> idTransactions) {
        this.idTransactions = idTransactions;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        String afisare =
                        "Account statement" + '\n' +
                        "ID: " + ID + '\n' +
                        "IdAccount: " + idAccount + '\n' +
                        "Current balance: " + balance + '\n' +
                        "Create Date: " + createDate.getTime() + '\n'+
                        "List of transaction: \n";
        for (int i : idTransactions) {
            afisare += TransactionService.getTransactionById(i).toString();
        }
        return afisare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatement that = (AccountStatement) o;
        return Double.compare(that.balance, balance) == 0 && Objects.equals(ID, that.ID) && Objects.equals(createDate, that.createDate) && Objects.equals(idAccount, that.idAccount) && Objects.equals(idTransactions, that.idTransactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, balance, createDate, idAccount, idTransactions);
    }
}
