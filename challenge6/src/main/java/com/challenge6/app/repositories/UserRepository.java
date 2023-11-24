package com.challenge6.app.repositories;

import com.challenge6.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

    public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByName(String username);

}
