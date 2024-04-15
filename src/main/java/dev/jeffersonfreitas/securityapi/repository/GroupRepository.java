package dev.jeffersonfreitas.securityapi.repository;

import dev.jeffersonfreitas.securityapi.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}
