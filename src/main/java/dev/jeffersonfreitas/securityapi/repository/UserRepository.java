package dev.jeffersonfreitas.securityapi.repository;

import dev.jeffersonfreitas.securityapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameIgnoreCase(String username);
}
