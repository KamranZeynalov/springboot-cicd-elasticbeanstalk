package com.company.cloudapp.service;

import com.company.cloudapp.entity.Role;
import com.company.cloudapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Long addRole(Role role) {
        return roleRepository.save(role).getId();
    }
}
