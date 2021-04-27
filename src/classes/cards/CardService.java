package classes.cards;

import classes.Audit;

import java.io.*;
import java.util.ArrayList;


public class CardService {
    public static ArrayList<VisaCard> cards = new ArrayList<>();


    public static void getCards() throws Exception {
        {
            Audit.write("getCards");
            String line = "";
            String splitBy = ",";
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/files/Cards.csv"));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(splitBy);

                    int id = Integer.parseInt(data[0]);
                    String cardNumber = data[1];
                    String createDate = data[2];
                    String expirationDate = data[3];
                    String pin = data[4];
                    String CVV2 = data[5];
                    int idAccount = Integer.parseInt(data[6]);
                    VisaCard v = new VisaCard(id, cardNumber, createDate, expirationDate, pin, CVV2, idAccount);
                    cards.add(v);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

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

    public static void write() {
        String data = "";
        try (PrintWriter writer = new PrintWriter(new File("src/files/Cards.csv"))) {
            for (VisaCard card : cards) {
                data += String.valueOf(card.getID()) + ',' +
                        card.getCardNumber() + ',' +
                        card.getCreateDate().getTime() + ',' +
                        card.getExpirationDate().getTime() + ',' +
                        card.getPin() + ',' +
                        card.getCVV2() + ',' +
                        card.getIdAccount() + '\n';
            }
            writer.write(data);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public static ArrayList<VisaCard> addCard(VisaCard card) {
        Audit.write("addCard");
        cards.add(card);
        write();
        return cards;
    }

    public static ArrayList<VisaCard> deleteCard(VisaCard card) {
        Audit.write("deleteCard");
        cards.remove(card);
        write();
        return cards;
    }

}
