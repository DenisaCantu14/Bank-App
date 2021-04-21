import classes.Client;
import classes.ClientService;
import classes.accounts.AccountService;
import classes.cards.CardService;

public class Main {

    public static void main(String[] args) throws Exception {
        ClientService.getClients();
        AccountService.getAccounts();
        CardService.getCards();
//
//        Client c = new Client("Denisa", "Cantu", "denisa.cantu@yahoo.com", "Str mihaiV", "0123456789", "012345678");
//        ClientService.addClient(c);
//        ClientService.displayClients();
        AccountService.write();

    }
}
