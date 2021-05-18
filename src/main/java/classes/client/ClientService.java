package  classes.client;

import classes.MySqlCon;
import main.java.classes.Audit;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ClientService {

    public static ArrayList<Client> clients = new ArrayList<>();

    public static void getClients() throws SQLException {
        Audit.write("getClients");

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from client");


        while (resultSet.next()){
            Client client = new Client (resultSet.getInt("idclient"),
                                        resultSet.getString("firstname"),
                                        resultSet.getString("lastname"),
                                        resultSet.getString("email"),
                                        resultSet.getString("address"),
                                        resultSet.getString("phonenumber"),
                                        resultSet.getString("personalcodenumber"));
            clients.add(client);
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

        public static ArrayList <Client> addClient (Client client) throws SQLException {
            Audit.write("addClient");
            clients.add(client);

            MySqlCon mySqlCon = new MySqlCon();
            Connection connection = mySqlCon.Connection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("INSERT INTO client values (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,   client.getID());
            preparedStatement.setString(2,client.getFirstName());
            preparedStatement.setString(3,client.getLastName());
            preparedStatement.setString(4,client.getEmail());
            preparedStatement.setString(5,client.getAddress());
            preparedStatement.setString(6,client.getPhoneNumber());
            preparedStatement.setString(7,client.getPersonalCodeNumber());
            preparedStatement.execute();
            return clients;
            
        }


        public static ArrayList<Client> deleteClient (Client client)
        {
            Audit.write("deleteClient");

            clients.remove(client);

            return clients;
        }



}
