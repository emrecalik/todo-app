package com.edoras.backend.repository;

import com.edoras.backend.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findAllByRoleNameIn(Set<Role.RoleName> roleNames);
}
