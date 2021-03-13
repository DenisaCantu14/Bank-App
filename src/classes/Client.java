package classes;

public class Client {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private long phoneNumber;
    private long personalCodeNumber;
    private Account account;

    public Client(String firstName, String lastName, String email, String address, long phoneNumber, long personalCodeNumber, Account account)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.personalCodeNumber = personalCodeNumber;
        this.account = new Account(account);
    }

}
