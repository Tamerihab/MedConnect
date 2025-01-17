package com.med.MedConnect.Model.User;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Default constructor
    public User() {
    }

    // Constructor for creating user objects
    public User(String firstName, String lastName, String nationalID, String email, String password, boolean isVolunteer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.email = email;
        this.password = password;
        this.isVolunteer = isVolunteer;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalID='" + nationalID + '\'' +
                ", email='" + email + '\'' +
                ", isVolunteer=" + isVolunteer +
                '}';
    }

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

