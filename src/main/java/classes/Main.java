package classes;

import  classes.cards.*;
import  classes.client.*;
import  classes.transactions.*;
import  classes.accounts.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        ClientService.getClients();
       // ClientService.displayClients();

        AccountService.getAccounts();
        //AccountService.displayAccounts();

        TransactionService.getTransactions();
       // TransactionService.displayTransactions();

        CardService.getCards();
       // CardService.displayCards();


        while (true) {
            System.out.println("MENU");
            System.out.println("1.Enter your card");
            System.out.println("2.Transfer money");
            System.out.println("3.Get account statement");
            System.out.println("4.Admin");
            System.out.println("5.Exit\n");
            System.out.println("Choose an option:");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                option1(scanner);
            }
            else if (option == 2) {
                option2(scanner);
            }
            else if (option == 3) {
                option3(scanner);
            }
            else if (option == 4) {
                option4(scanner);
            } else break;
        }
    }
    private static void option1(Scanner scanner) throws SQLException, MyException {
        while (true) {
            System.out.println("Enter card number:");
            String cardID = scanner.next();
            System.out.println("Enter pin:");
            String pin = scanner.next();

            VisaCard card = CardService.getCard(cardID, pin);

            if (card == null) {
                System.out.println("Your data is incorrect!");
            } else {
                Integer AccountId = card.getIdAccount();
                Account account = AccountService.getAccountById(AccountId);

                System.out.println("1.WithdrawMoney");
                System.out.println("2.Deposit money");
                System.out.println("3.Check sold");
                System.out.println("4.Reset pin\n");

                int option = scanner.nextInt();

                if (option == 1) {
                    System.out.println("Enter the amount");
                    Double amount = scanner.nextDouble();

                    try {
                        AccountService.withdraw(amount, account.getID());
                    } catch (MyException e) {
                        System.out.println(e);
                    }
                    System.out.println("Done!");
                    break;

                } else if (option == 2) {
                    System.out.println("Enter the amount");
                    Double amount = scanner.nextDouble();
                    AccountService.deposit(amount, account.getID());
                    System.out.println("Done!");
                    break;
                } else if (option == 3) {
                    System.out.println(account.getBalance());
                    break;
                }
                else{
                    System.out.println("Enter your new pin:");
                    String newPin = scanner.next();
                    CardService.updatePin(card.getID(), newPin);
                    System.out.println("Done!");
                    break;
                }
            }
        }
    }
    private static void option2(Scanner scanner) throws SQLException, ParseException {
        while (true) {
            System.out.println("Enter your personal code number");
            String Cnp = scanner.next();
            Client client = ClientService.getClientByCnp(Cnp);

            if (client == null) {
                System.out.println("Your data is incorrect");
            } else {
                Integer ClientId = client.getID();
                Account account = AccountService.getAccountByClientId(ClientId);

                if (account == null || account instanceof Deposit) {
                    System.out.println("There is no account");
                } else {
                    while (true) {
                        System.out.println("Enter IBAN to transfer: ");
                        String IBAN = scanner.next();
                        Account targetAccount = AccountService.getAccountByIban(IBAN);
                        if (targetAccount == null)
                            System.out.println("There is no account with this IBAN");
                        else {
                            System.out.println("Enter the amount:");
                            Double amount = scanner.nextDouble();
                            try {
                                AccountService.withdraw(amount, account.getID());
                                AccountService.deposit(amount, targetAccount.getID());
                                Transfer newTransfer = new Transfer(account.getID(), targetAccount.getID(), amount);
                                TransactionService.addTransaction(newTransfer);
                                System.out.println("Done!");
                                break;
                            } catch (MyException e) {
                                System.out.println(e);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
    private static void option3(Scanner scanner) throws Exception {
        while (true) {

            System.out.println("Enter your personal code number");
            String Cnp = scanner.next();
            Client client = ClientService.getClientByCnp(Cnp);

            if (client == null) {
                System.out.println("Your data is incorrect");
            }
            else{
                Account account = AccountService.getAccountByClientId(client.getID());

                if (account == null) {
                    System.out.println("There is no account");
                } else {
                    AccountStatement st = new AccountStatement(account.getID());
                    System.out.println(st);
                    break;
                }}

        }
    }
    private static void option4(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("1.Add anew client");
            System.out.println("2.Add a new account");
            System.out.println("3.Add a new credit card");
            System.out.println("4.Update client information");
            System.out.println("5.Exit\n");
            System.out.println("Choose an option:");

            int option = scanner.nextInt();
            if (option == 1) {
                System.out.println("Enter first name:");
                String firstName = scanner.next();
                System.out.println("Enter last name:");
                String lastName = scanner.next();
                System.out.println("Enter email:");
                String email = scanner.next();
                System.out.println("Enter address:");
                String address = scanner.next();
                System.out.println("Enter phone number:");
                String phoneNumber = scanner.next();
                System.out.println("Enter personal code number:");
                String personalCodeNumber = scanner.next();
                Client c = new Client(firstName, lastName, email, address, phoneNumber, personalCodeNumber);
                ClientService.addClient(c);
            }
            else if (option == 2) {
                while (true) {
                    System.out.println("Enter CNP");
                    String Cnp = scanner.next();

                    Client client = ClientService.getClientByCnp(Cnp);

                    if (client == null) {
                        System.out.println("Your data is incorrect");
                    } else {
                        System.out.println("Enter type of account (1 or 2):\n 1.Current\n 2.Deposit");
                        Integer accountType = scanner.nextInt();
                        System.out.println("Enter the amount:");
                        Double amount = scanner.nextDouble();
                        if (accountType == 2) {
                            System.out.println("Period (months): ");
                            Integer nrMonths = scanner.nextInt();
                            Account deposit = new Deposit(amount, client.getID(), nrMonths);
                            AccountService.addAccount(deposit);

                        } else {
                            Account current = new Current(amount, client.getID());
                            AccountService.addAccount(current);
                        }
                        System.out.println("Done!");
                        break;
                    }
                }
            }
            else if (option == 3) {
                while (true) {
                    System.out.println("Enter CNP");
                    String Cnp = scanner.next();

                    Client client = ClientService.getClientByCnp(Cnp);

                    if (client == null) {
                        System.out.println("Your data is incorrect");
                    } else {
                        Account account = AccountService.getAccountByClientId(client.getID());
                        if (account == null || account instanceof Deposit) {
                            System.out.println("There is no account open for this client");
                        } else {
                            System.out.println("Enter pin (4 digits)");
                            String pin = scanner.next();
                            VisaCard card = new VisaCard(pin, account.getID());
                            System.out.println("Done");
                            break;
                        }
                    }
                }
            }
            else if (option == 4) {
                while (true) {
                    System.out.println("Enter CNP");
                    String Cnp = scanner.next();

                    Client client = ClientService.getClientByCnp(Cnp);

                    if (client == null) {
                        System.out.println("Your data is incorrect");
                    } else {
                        while(true)
                        {
                            System.out.println("1.Update email");
                            System.out.println("2.Update address");
                            System.out.println("3.Update phone number");
                            System.out.println("4.Delete account");
                            System.out.println("5.Delete client");
                            System.out.println("6.Exit");

                            int op = scanner.nextInt();

                            if(op == 1)
                            {
                                System.out.println("Enter the new email:");
                                String email = scanner.next();
                                ClientService.updateEmail(email, client.getID());
                            }
                            else if(op == 2)
                            {
                                System.out.println("Enter the new address:");
                                String address = scanner.next();
                                ClientService.updateAddress(address, client.getID());
                            }
                            else if(op == 3)
                            {
                                System.out.println("Enter the new phone number:");
                                String phoneNumber = scanner.next();
                                ClientService.updatePhoneNumber(phoneNumber, client.getID());
                            }
                            else if(op == 4){
                                Account account = AccountService.getAccountByClientId(client.getID());
                                System.out.println(client.getID());
                                AccountService.deleteAccount(account);
                                System.out.println("Done!");
                                break;
                            }
                            else if(op == 5){
                                ClientService.deleteClient(client);
                                System.out.println("Done!");
                                break;
                            }
                            else break;
                        }
                    }
                }
            }
            else if (option == 5) {
                break;
            }
        }
    }



}

