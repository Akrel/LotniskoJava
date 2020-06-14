package com.example.model.communication;

import com.example.model.database.User;

import java.io.Serializable;
/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest odpowiedzią na żądanie klienta przechowuje infromacje pobrane z bazy dotyczące utworzenia użytkownika
 */
public class CreateUserResponse implements Serializable {

    private String status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;


    public CreateUserResponse(String status, User user) {
        this.status = status;
        this.user = user;
    }

    public CreateUserResponse(String status) {
        this.status = status;
    }

    public CreateUserResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
