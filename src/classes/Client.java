package classes;

import java.util.ArrayList;
import java.util.Objects;

public class Client implements Comparable<Client>{
    public static int nrClients = 0;
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String personalCodeNumber;
    private ArrayList<Integer> accounts;

    public Client(String firstName, String lastName, String email, String address, String phoneNumber, String personalCodeNumber)
    {
        this.ID = nrClients++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personalCodeNumber = personalCodeNumber;
        this.accounts = new ArrayList<>();
    }
    public Client(int ID, String firstName, String lastName, String email, String address, String phoneNumber, String personalCodeNumber, ArrayList<Integer> accounts)
    {
        this.ID = ID;
        nrClients++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personalCodeNumber = personalCodeNumber;
        this.accounts = accounts;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPersonalCodeNumber() {
        return personalCodeNumber;
    }

    public void setPersonalCodeNumber(String personalCodeNumber) {
        this.personalCodeNumber = personalCodeNumber;
    }

    public ArrayList<Integer> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Integer> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        String afisare =   "ID " + ID + '\n' +
                "First name: " + firstName  + '\n' +
                "Last name: " + lastName  + '\n' +
                "Email: " + email  + '\n' +
                "Address: " + address  + '\n' +
                "Phone number: " + phoneNumber + '\n' +
                "Personal code number: " + personalCodeNumber + '\n';
//        for (Integer account : accounts)
//        {
//            afisare += account.toString();
//        }
        return  afisare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return ID == client.ID && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(email, client.email) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(personalCodeNumber, client.personalCodeNumber) && Objects.equals(accounts, client.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, email, address, phoneNumber, personalCodeNumber, accounts);
    }

    @Override
    public int compareTo(Client client) {
        int i = lastName.compareTo(client.lastName);
        if (i != 0) return i;
        i = firstName.compareTo(client.firstName);
        return i;

    }
}
