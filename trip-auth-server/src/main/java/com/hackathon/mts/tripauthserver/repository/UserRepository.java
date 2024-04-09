package com.hackathon.mts.tripauthserver.repository;

import com.hackathon.mts.tripauthserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
