package classes;

public class Bank {
    private static Bank instance;

    private String name;
    private String address;
    private int code;

    private Bank(String name, String address, int code)
    {
        this.name = name;
        this.address = address;
        this.code = code;
    }

    public static Bank getInstance(String name, String address, int code)
    {
        if(instance == null)
            instance = new Bank(name, address, code);
        return instance;

    }
}

