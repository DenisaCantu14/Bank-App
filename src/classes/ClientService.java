package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ClientService {

        public static ArrayList<Client> clients = new ArrayList<>();


        public static void getClients() throws Exception {
            {
                String line = "";
                String splitBy = ",";
                try {
                    BufferedReader br = new BufferedReader(new FileReader("src/files/Clients.csv"));
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(splitBy);
                        String firstName = data[0];
                        String lastName = data[1];
                        String email = data[2];
                        String address = data[3];
                        String phoneNumber = data[4];
                        String personalCode = data[5];
                        Client c = new Client(firstName, lastName, email, address, phoneNumber, personalCode);
                        clients.add(c);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public static void displayClients ()
        {
            clients.sort(Comparator.comparing(Client::getLastName));

            System.out.println("List of clients: \n");
            for ( Client client : clients)
            {
                System.out.println(client.toString());
            }
        }
        public Client getClientById (int id)
        {
            for ( Client client : clients)
            {
                if ( client.getID() == id) {
                    return client;
                }
            }
            return null;
        }

        public Client getClient (Client c )
        {
            for ( Client client : clients)
            {
                if ( client.equals(c) ) {
                    return client;
                }

            }
            return null;
        }

        public ArrayList <Client> addClient (Client client)
        {
            clients.add(client);
            return clients;
        }

        public ArrayList<Client> deleteClient (ArrayList<Client> clients, Client client)
        {
            clients.remove(client);
            return clients;
        }



}
