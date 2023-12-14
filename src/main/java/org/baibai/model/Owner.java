package org.baibai.model;

public class Owner {
    private int ownerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public Owner(int ownerId, String firstName, String lastName, String address, String phone) {
        this(firstName, lastName, address, phone);
        this.ownerId = ownerId;
    }

    public Owner(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getLastName() {
        return lastName;
    }
}
