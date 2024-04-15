package dev.jeffersonfreitas.securityapi.repository;

import dev.jeffersonfreitas.securityapi.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
