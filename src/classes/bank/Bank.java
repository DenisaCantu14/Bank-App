package classes.bank;

import java.util.Objects;

public class Bank {

    private String name;
    private String address;
    private int code;

    public Bank(String name, String address, int code)
    {
        this.name = name;
        this.address = address;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Bank: " +  '\n' +
                "Name: " + name  + '\n' +
                "Address: " + address +  '\n' +
                "Code: " + code +  '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return code == bank.code && Objects.equals(name, bank.name) && Objects.equals(address, bank.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, code);
    }
}

