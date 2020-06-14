package com.example.model.communication;

import java.io.Serializable;
/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest żądaniem od klienta do serwera informacji dotyczących logowania użytkownika.
 */
public class LoginUserRequest implements Serializable {

    private String email;
    private String password;

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
}
