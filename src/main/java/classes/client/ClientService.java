package  classes.client;

import classes.MySqlCon;
import classes.accounts.AccountService;
import main.java.classes.Audit;
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

        public static void updateEmail(String email, int id) throws SQLException {

            MySqlCon mySqlCon = new MySqlCon();
            Connection connection = mySqlCon.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE banckingapp.client SET email = ? WHERE idclient = ?;");
            preparedStatement.setString(1,email);
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
        }
    public static void updatePhoneNumber(String phoneNr, int id) throws SQLException {

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE banckingapp.client SET phonenumber = ? WHERE idclient = ?;");
        preparedStatement.setString(1,phoneNr);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();}
    public static void updateAddress(String address, int id) throws SQLException {

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE banckingapp.client SET address = ? WHERE idclient = ?;");
        preparedStatement.setString(1,address);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();}

    public static void deleteClient (Client client) throws SQLException {
            Audit.write("deleteClient");
            clients.remove(client);
            MySqlCon mySqlCon = new MySqlCon();
            Connection connection = mySqlCon.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM banckingapp.client WHERE idclient = ?;");
            preparedStatement.setInt(1,client.getID());
            preparedStatement.execute();
        }



}
