import classes.MyException;
import classes.accounts.*;
import classes.cards.CardService;
import classes.cards.VisaCard;
import classes.client.Client;
import classes.client.ClientService;
import classes.transactions.TransactionService;
import classes.transactions.Transfer;

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
                    Account account = AccountService.getAccountById(AccountId);
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
                            try {
                                AccountService.withdraw(amount, account.getID());
                            } catch (MyException e) {
                                System.out.println(e);
                            }
                            System.out.println("Done!");
                            break;

                        } else if (option1 == 2) {
                            System.out.println("Enter the amount");
                            Double amount = scanner.nextDouble();
                            AccountService.deposit(amount, AccountId);
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
                    Client client = ClientService.getClientByCnp(Cnp);

                    if (client == null) {
                        System.out.println("Your data is incorrect");
                    } else {
                        Integer ClientId = client.getID();
                        Account account = AccountService.getAccountByClientId(ClientId);
                        System.out.println(account);

                        if (account == null || account instanceof Deposit) {
                            System.out.println("There is no account");
                        } else {

                            while (true) {
                                System.out.println("Enter IBAN to transfer: ");
                                String IBAN = scanner.next();
                                Account targetAccount = AccountService.getAccountByIban(IBAN);
                                System.out.println(targetAccount);
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

            } else if (option == 3) {
                while (true) {
                    System.out.println("Enter CNP");
                    String Cnp = scanner.next();

                    Integer ClientId = ClientService.getClientByCnp(Cnp).getID();
                    Integer AccountId = AccountService.getAccountByClientId(ClientId).getID();
                    Account account = AccountService.getAccountById(AccountId);

                    if (account == null) {
                        System.out.println("Your data is incorrect");
                    } else {

                        System.out.println("Enter start date");
                        StringBuilder date = new StringBuilder("");
                        for (int i = 0; i <= 5; i++) {
                            String str = scanner.next();
                            date.append(str);
                            if (i != 5)
                                date.append(" ");


                        }
                        System.out.println(date);
                        AccountStatement st = new AccountStatement(AccountId, date.toString());
                        AccountStatementService.addAccountStatement(st);
                        System.out.println(st);
                        break;
                    }
                }
            } else if (option == 4) {
                while (true) {
                    System.out.println("1.Add anew client");
                    System.out.println("2.Add a new account");
                    System.out.println("3.Add a new credit card");
                    System.out.println("4.Exit");
                    System.out.println("Choose an option:");
                    int adminOption = scanner.nextInt();
                    if (adminOption == 1) {
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
                    } else if (adminOption == 2) {
                        while (true) {
                            System.out.println("Enter CNP");
                            String Cnp = scanner.next();

                            Client client = ClientService.getClientByCnp(Cnp);
                            Integer clientId = client.getID();


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
                                    Account deposit = new Deposit(amount, clientId, nrMonths);
                                    AccountService.addAccount(deposit);

                                } else {
                                    Account current = new Current(amount, clientId);
                                    AccountService.addAccount(current);
                                }
                                System.out.println("Done!");
                                break;
                            }
                        }
                    } else if (adminOption == 3) {
                        while (true) {
                            System.out.println("Enter CNP");
                            String Cnp = scanner.next();

                            Client client = ClientService.getClientByCnp(Cnp);
                            Integer clientId = client.getID();


                            if (client == null) {
                                System.out.println("Your data is incorrect");
                            } else {
                                Account account = AccountService.getAccountByClientId(clientId);
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
                    } else if (adminOption == 4) {
                        break;
                    }
                }
            } else break;
        }
    }
}

