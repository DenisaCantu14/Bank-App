package classes;

import java.util.Objects;

public class Client implements Comparable<Client>{
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private long phoneNumber;
    private long personalCodeNumber;

    public Client(String firstName, String lastName, String email, String address, long phoneNumber, long personalCodeNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personalCodeNumber = personalCodeNumber;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getPersonalCodeNumber() {
        return personalCodeNumber;
    }

    public void setPersonalCodeNumber(long personalCodeNumber) {
        this.personalCodeNumber = personalCodeNumber;
    }

    @Override
    public String toString() {
        return  "First name: " + firstName  + '\n' +
                "Last name: " + lastName  + '\n' +
                "Email: " + email  + '\n' +
                "Adress: " + address  + '\n' +
                "Phone number: " + phoneNumber + '\n' +
                "Personal code number: " + personalCodeNumber + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return phoneNumber == client.phoneNumber && personalCodeNumber == client.personalCodeNumber && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(email, client.email) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, address, phoneNumber, personalCodeNumber);
    }


    @Override
    public int compareTo(Client client) {
        int i = lastName.compareTo(client.lastName);
        if (i != 0) return i;
        i = firstName.compareTo(client.firstName);
        return i;

    }
}
