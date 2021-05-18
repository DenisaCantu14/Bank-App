package main.java.classes;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Audit {
    public static void write(String actionName) {
        try {
            String filename = "src/main/java/files/Audit.csv";
            FileWriter writer = new FileWriter(filename, true);
            Calendar date = new GregorianCalendar();
            String data = actionName + "," + date.getTime() + '\n';
            writer.write(data);
            writer.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }


}
