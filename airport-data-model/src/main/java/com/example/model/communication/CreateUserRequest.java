package com.example.model.communication;

import java.io.Serializable;
/**
 * Klasa jest modelem komunikacji pomiędzy serwerem a klientem,
 * klasa jest żądaniem od klienta do serwera informacji dotyczących użytkownika.
 */
public class CreateUserRequest implements Serializable {
    private String name;

    private String surname;

    private String email;

    private String phone;

    private String password;

    private Integer id;

    private String type;

    public String getTypeCreate() {
        return type;
    }

    public void setTypeCreate(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
