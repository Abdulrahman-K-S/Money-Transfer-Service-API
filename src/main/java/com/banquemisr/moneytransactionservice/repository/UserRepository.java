package com.banquemisr.moneytransactionservice.repository;

import com.banquemisr.moneytransactionservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // USER
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    // BOOLEAN
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
