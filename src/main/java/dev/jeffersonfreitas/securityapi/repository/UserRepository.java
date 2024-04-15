package dev.jeffersonfreitas.securityapi.repository;

import dev.jeffersonfreitas.securityapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
