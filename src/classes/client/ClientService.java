package classes.client;

import classes.Audit;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ClientService {

        public static ArrayList<Client> clients = new ArrayList<>();


        public static void getClients() throws Exception {

            {
                Audit.write("getClients");

                String line = "";
                String splitBy = ",";
                try {
                    BufferedReader br = new BufferedReader(new FileReader("src/files/Clients.csv"));
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(splitBy);
                        int id = Integer.parseInt(data[0]);
                        String firstName = data[1];
                        String lastName = data[2];
                        String email = data[3];
                        String address = data[4];
                        String phoneNumber = data[5];
                        String personalCode = data[6];
                        Client c = new Client(id, firstName, lastName, email, address, phoneNumber, personalCode);
                        clients.add(c);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public static void displayClients ()

        {
            Audit.write("displayClients");

            clients.sort(Comparator.comparing(Client::getLastName));

            System.out.println("List of clients: \n");
            for ( Client client : clients)
            {
                System.out.println(client.toString());
            }
        }
        public static Client getClientById (int id)
        {
            for ( Client client : clients)
            {
                if ( client.getID() == id) {
                    return client;
                }
            }
            return null;
        }

    public static Client getClientByCnp (String CNP)
    {
        for ( Client client : clients)
        {
            if ( client.getPersonalCodeNumber().equals(CNP)) {
                return client;
            }
        }
        return null;
    }

        public static ArrayList <Client> addClient (Client client)
        {
            Audit.write("addClient");

            System.out.println("heeei");
            clients.add(client);
            write();
            return clients;
            
        }

        public static void write()
        {
            String data = "";
            try (PrintWriter writer = new PrintWriter(new File("src/files/Clients.csv")))
            {
                for (Client client : clients) {

                    data += String.valueOf(client.getID()) + ',' +
                            client.getFirstName() + ',' +
                            client.getLastName() + ',' +
                            client.getEmail() + ',' +
                            client.getAddress() + ',' +
                            client.getPhoneNumber() + ',' +
                            client.getPersonalCodeNumber() + '\n';
                }
                writer.write(data);
            }
            catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
        }

       
        public static ArrayList<Client> deleteClient (Client client)
        {
            Audit.write("deleteClient");

            clients.remove(client);
            write();
            return clients;
        }



}
