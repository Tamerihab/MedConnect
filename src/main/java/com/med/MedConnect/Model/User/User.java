package com.med.MedConnect.Model.User;

import com.med.MedConnect.Model.Address.Address;  // Import the Address class
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userID;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String nationalID;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private boolean isVolunteer;

    @ManyToOne  // Many users can have one address (many-to-one relationship)
    @JoinColumn(name = "address_id")  // This will be the foreign key column in the user table
    private Address address;  // The user's address (city)

    // Default constructor
    public User() {
    }

    // Constructor for creating user objects
    public User(String firstName, String lastName, String nationalID, String email, String password, boolean isVolunteer, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.email = email;
        this.password = password;
        this.isVolunteer = isVolunteer;
        this.address = address;  // Assign address to the user
    }

    // Getters and setters
    public int getId() {
        return userID;
    }

    public void setId(int id) {
        this.userID = id;
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

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVolunteer() {
        return isVolunteer;
    }

    public void setVolunteer(boolean volunteer) {
        isVolunteer = volunteer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalID='" + nationalID + '\'' +
                ", email='" + email + '\'' +
                ", isVolunteer=" + isVolunteer +
                ", address=" + address +  // Include the address in the toString method
                '}';
    }

    // Additional functionality for volunteer role
    public void addVolunteerRole() {
        if (!this.isVolunteer) {
            this.isVolunteer = true;
            System.out.println("Volunteer role added successfully.");
        } else {
            System.out.println("User is already a volunteer.");
        }
    }

    public void removeVolunteerRole() {
        if (this.isVolunteer) {
            this.isVolunteer = false;
            System.out.println("Volunteer role removed successfully.");
        } else {
            System.out.println("User is not currently a volunteer.");
        }
    }
}
