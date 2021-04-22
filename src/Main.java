import classes.accounts.*;
import classes.cards.CardService;
import classes.cards.VisaCard;
import classes.client.Client;
import classes.client.ClientService;
import classes.transactions.Transaction;
import classes.transactions.TransactionService;
import classes.transactions.Transfer;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        ClientService.getClients();
        TransactionService.getTransactions();
        AccountService.getAccounts();
        CardService.getCards();
        AccountStatementService.getAccountStatements();

        while (true) {
            System.out.println("MENU");
            System.out.println("1.Enter your card");
            System.out.println("2.Transfer money");
            System.out.println("3.Get account statement");
            System.out.println("4.Admin");
            System.out.println("5.Exit");
            System.out.println("Choose an option:");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                while (true) {
                    System.out.println("Enter card number:");
                    String cardID = scanner.next();
                    System.out.println("Enter pin:");
                    String pin = scanner.next();

                    Integer AccountId = CardService.getCard(cardID, pin).getIdAccount();
                    Account account =  AccountService.getAccountById(AccountId);
                    if (AccountId == null) {
                        System.out.println("Your data is incorrect!");
                    } else {
                        System.out.println("1.WithdrawMoney");
                        System.out.println("2.Deposit money");
                        System.out.println("3.Check sold");
                        int option1 = scanner.nextInt();
                        if (option1 == 1) {
                            System.out.println("Enter the amount");
                            Double amount = scanner.nextDouble();
                            System.out.println(amount);
                            ((Current) account).withdraw(amount);
                            System.out.println("Done!");
                            break;

                        } else if (option1 == 2) {
                            System.out.println("Enter the amount");
                            Double amount = scanner.nextDouble();
                            account.deposit(amount);
                            System.out.println("Done!");
                            break;
                        } else {
                            System.out.println(account.getBalance());
                            break;
                        }
                    }
                }

            } else if (option == 2) {
                while (true) {
                    System.out.println("Enter CNP");
                    String Cnp = scanner.next();

                    Integer ClientId = ClientService.getClientByCnp(Cnp).getID();

                    Integer AccountId = AccountService.getAccountByClientId(ClientId).getID();
                    Account account = AccountService.getAccountById(AccountId);
                    if (AccountId == null) {
                        System.out.println("Your data is incorrect");
                    } else {
                        break;
                    }


                }
            } else if (option == 4) {
                while (true) {

                }
            } else {
                break;
            }

        }
    }
}

