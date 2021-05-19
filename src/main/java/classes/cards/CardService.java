package classes.cards;

import classes.MySqlCon;
import  main.java.classes.Audit;
import java.sql.*;
import java.util.ArrayList;


public class CardService {
    public static ArrayList<VisaCard> cards = new ArrayList<>();


    public static void getCards() throws Exception
        {
            Audit.write("getCards");
            MySqlCon mySqlCon = new MySqlCon();
            Connection connection = mySqlCon.Connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from visacard");


            while (resultSet.next()){
                VisaCard card = new VisaCard (resultSet.getInt("idvisacard"),
                        resultSet.getString("cardnumber"),
                        resultSet.getString("createdate"),
                        resultSet.getString("expirationdate"),
                        resultSet.getString("pin"),
                        resultSet.getString("cvv2"),
                        resultSet.getInt("idaccount"));
                cards.add(card);
            }

        }


    public static void displayCards() {

        Audit.write("displayCards");

        System.out.println("List of cards: \n");
        for (VisaCard card : cards) {
            System.out.println(card.toString());
        }
    }

    public static VisaCard getCardById(int id) {
        for (VisaCard card : cards) {
            if (card.getID() == id) {
                return card;
            }
        }
        return null;
    }

    public static VisaCard getCard(String number, String pin) {
        for (VisaCard card : cards) {
            if (card.getCardNumber().equals(number) && card.getPin().equals(pin)) {
                return card;
            }
        }
        return null;
    }




    public static ArrayList<VisaCard> addCard(VisaCard card) throws SQLException {
        Audit.write("addCard");
        cards.add(card);

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("INSERT INTO visacard values (?,?,?,?,?,?,?)");
        preparedStatement.setInt(1,   card.getID());
        preparedStatement.setString(2,card.getCardNumber());
        preparedStatement.setString(3, String.valueOf(card.getCreateDate().getTime()));
        preparedStatement.setString(4, String.valueOf(card.getExpirationDate().getTime()));
        preparedStatement.setString(5,card.getPin());
        preparedStatement.setString(6,card.getCVV2());
        preparedStatement.setInt(7,card.getIdAccount());
        preparedStatement.execute();
        return cards;
    }


    public static void updateCard(VisaCard card) throws SQLException {

        MySqlCon mySqlCon = new MySqlCon();
        Connection connection = mySqlCon.Connection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE banckingapp.visacard SET cardnumber = ?, createdate = ?, expirationdate = ?, pin = ?, cvv2 = ?, idaccount = ? WHERE (idvisacard = ?);");
        preparedStatement.setString(1,card.getCardNumber());
        preparedStatement.setDate(2, (Date) card.getCreateDate().getTime());
        preparedStatement.setDate(3, (Date) card.getExpirationDate().getTime());
        preparedStatement.setString(4,card.getPin());
        preparedStatement.setString(5,card.getCVV2());
        preparedStatement.setInt(6,card.getIdAccount());
        preparedStatement.setInt(7,card.getID());
        preparedStatement.execute();
    }


    public static ArrayList<VisaCard> deleteCard(VisaCard card) {
        Audit.write("deleteCard");
        cards.remove(card);

        return cards;
    }

}
