package com.example.mainserverpackage;

import com.example.model.database.User;
import org.springframework.data.repository.CrudRepository;


/**
 * Interfejs pozwalający na tworzenie zapytań na podstawie nazw metod do tabeli Usera
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Iterable<User> findByEmail(String email);

    User findUserByEmail(String email);

}
