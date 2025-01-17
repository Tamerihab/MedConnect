package com.med.MedConnect.services.strategy.login;

public interface LoginStrategy {
    boolean login(String email, String password);
}
