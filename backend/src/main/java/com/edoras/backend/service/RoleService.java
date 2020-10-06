package com.edoras.backend.service;

import com.edoras.backend.domain.Role;
import com.edoras.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getAllRolesByRoleNameIn(Set<Role.RoleName> roleNameSet) {
        return roleRepository.findAllByRoleNameIn(roleNameSet);
    }
}
