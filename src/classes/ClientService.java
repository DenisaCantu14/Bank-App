package classes;

import classes.accounts.AccountStatement;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

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
                        int id = Integer.parseInt(data[0]);
                        String firstName = data[1];
                        String lastName = data[2];
                        String email = data[3];
                        String address = data[4];
                        String phoneNumber = data[5];
                        String personalCode = data[6];
                        String [] accountsIds = data[7].split(" ");
                        ArrayList<Integer> ids = new ArrayList<>();
                        for(String s : accountsIds)
                        {
                            ids.add(Integer.parseInt(s));
                        }
                        Client c = new Client(id, firstName, lastName, email, address, phoneNumber, personalCode, ids);
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

        public static Client getClient (Client c )
        {
            for ( Client client : clients)
            {
                if ( client.equals(c) ) {
                    return client;
                }

            }
            return null;
        }

        public static ArrayList <Client> addClient (Client client)
        {
            clients.add(client);
            write();
            return clients;
            
        }

        public static void write()
        {
            for (Client client : clients)
            {
                try (PrintWriter writer = new PrintWriter(new File("src/files/Clients.csv"))) {
                    System.out.println(client.getID());
                    String data = String.valueOf(client.getID()) + ',' +
                            client.getFirstName() + ',' +
                            client.getLastName() + ',' +
                            client.getEmail() + ',' +
                            client.getAddress() + ',' +
                            client.getPhoneNumber() + ',' +
                            client.getPersonalCodeNumber() + ',';

                     for( Integer id: client.getAccounts())
                     {
                         data += String.valueOf(id) + " ";
                     }
                     data += '\n';


                    writer.write(data);
                    System.out.println("done!");

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

       
        public static ArrayList<Client> deleteClient (ArrayList<Client> clients, Client client)
        {
            clients.remove(client);
            write();
            return clients;
        }



}
