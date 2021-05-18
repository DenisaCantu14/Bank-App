Clase implementate:

- Account (clasa abstracta)
- Current (mosteneste din clasa Account)
- Deposit (mosteneste din clasa Account)
- VisaCard 
- Client
- Transaction
- Transefer (mosteneste din clasa Transaction)
- AccountStatement (extrasul de cont)
- AccountService (clasa serviciu pentru clasa Account)
- ClientService (clasa serviciu pentru clasa Client)
- TransactionService (clasa serviciu pentru clasa Transaction)

Actiuni / Interogari:

ClientService
-  public static void displayClients (Client[] clients)  -- afiseaza lista clienti
-  public static void getClient (Client[] clients, Client c ) --afiseaza un client anume
-  public static Client[] addClient (Client[] clients, Client client) --adauga un client in lista 

AccountService
-  public static void displayAccounts (ArrayList<Account> accounts) -- afiseaza lista conturi
-  public static void getAccount (ArrayList<Account> accounts, Account a) --afiseaza un cont anume
-  public static ArrayList<Account> addAccount (ArrayList<Account> accounts, Account account) --adauga un cont in lista
-  public static ArrayList<Account> deleteAccount (ArrayList<Account> accounts, Account account) --sterge un cont din lista

TransactionService
- public static void displayTransactions (ArrayList<Transaction> transactions) -- afiseaza lista tranzactii
- public static ArrayList<Transaction> addTransaction (ArrayList<Transaction> transactions, Transaction transaction) --adauga o tranzactie in lista
- public static ArrayList<Transaction> deleteTransaction (ArrayList<Transaction> transactions, Transaction transaction) --sterge o tranzactie din lista
