package classes.cards;

import java.io.*;
import java.util.ArrayList;


public class CardService {
    public static ArrayList<VisaCard> cards = new ArrayList<>();


    public static void getCards() throws Exception {
        {
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

                    VisaCard v = new VisaCard(id, cardNumber, createDate, expirationDate, pin, CVV2);
                    cards.add(v);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void displayCards ()
    {


        System.out.println("List of cards: \n");
        for ( VisaCard card : cards)
        {
            System.out.println(card.toString());
        }
    }
    public static VisaCard getCardById (int id)
    {
        for ( VisaCard card : cards)
        {
            if ( card.getID() == id) {
                return card;
            }
        }
        return null;
    }

    public VisaCard getCard (VisaCard c )
    {
        for ( VisaCard card : cards)
        {
            if ( card.equals(c) ) {
                return card;
            }

        }
        return null;
    }
    public static void write()
    {
        for (VisaCard card : cards)
        {
            try (PrintWriter writer = new PrintWriter(new File("src/files/Cards.csv"))) {

                String data = String.valueOf(card.getID()) + ',' +
                        card.getCardNumber() + ',' +
                        card.getCreateDate() + ',' +
                        card.getExpirationDate() + ',' +
                        card.getPin() + ',' +
                        card.getCVV2() + ',' + '\n';

                writer.write(data);
                System.out.println("done!");

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public ArrayList <VisaCard> addCard (VisaCard card)
    {
        cards.add(card);
        write();
        return cards;
    }

    public ArrayList<VisaCard> deleteCard (ArrayList<VisaCard> cards, VisaCard card)
    {
        cards.remove(card);
        write();
        return cards;
    }

}
