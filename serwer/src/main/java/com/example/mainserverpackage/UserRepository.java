package com.example.mainserverpackage;

import com.example.model.database.User;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    Iterable<User> findByEmail(String email);
    User findUserByEmail(String email);
}
