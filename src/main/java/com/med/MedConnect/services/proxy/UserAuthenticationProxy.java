package com.med.MedConnect.services.proxy;

import com.med.MedConnect.Model.User.User;

public class UserAuthenticationProxy {
    private final User user;
    private boolean isAuthenticated;

    public UserAuthenticationProxy(User user) {
        this.user = user;
        this.isAuthenticated = false; // Default to not authenticated
    }

    // Method to authenticate the user
    public boolean authenticate(String email, String password) {
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            isAuthenticated = true;
            System.out.println("Authentication successful for user: " + user.getFirstName());
            return true;
        } else {
            System.out.println("Authentication failed.");
            return false;
        }
    }

    // Method to get user details if authenticated
    public String getUserDetails() {
        if (isAuthenticated) {
            return user.toString();
        } else {
            return "Access denied. Please authenticate first.";
        }
    }

    // Proxy method to add a volunteer role
    public void addVolunteerRole() {
        if (isAuthenticated) {
            user.addVolunteerRole();
        } else {
            System.out.println("Access denied. Please authenticate first.");
        }
    }

    public void removeVolunteerRole() {
        if (isAuthenticated) {
            user.removeVolunteerRole();
        } else {
            System.out.println("Access denied. Please authenticate first.");
        }
    }

    // Method to log out
    public void logout() {
        isAuthenticated = false;
        System.out.println("User logged out successfully.");
    }
}