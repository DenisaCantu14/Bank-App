package classes;

import java.util.Arrays;

public class ClientService {

        public static void displayClients (Client[] clients)
        {
            Arrays.sort(clients);
            System.out.println("List of clients: \n");
            for ( Client client : clients)
            {
                System.out.println(client.toString());
            }
        }

        public static void getClient (Client[] clients, Client c )
        {
            for ( Client client : clients)
            {
                if ( client.equals(c) ) {
                    System.out.println("The client is: ");
                    System.out.println(client.toString());
                    break;
                }

            }
        }

        public static Client[] addClient (Client[] clients, Client client) {
            Client[] newListOfClients = new Client[clients.length + 1];
            for(int i = 0; i < clients.length; i++)
                newListOfClients[i] = clients[i];
            newListOfClients[clients.length] = client;
            return newListOfClients;
        }



}
