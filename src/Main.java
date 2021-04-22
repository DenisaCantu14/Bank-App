import classes.client.ClientService;
import classes.accounts.AccountService;
import classes.cards.CardService;

public class Main {

    public static void main(String[] args) throws Exception {
        ClientService.getClients();
        AccountService.getAccounts();
        CardService.getCards();


        AccountService.write();

    }
}

