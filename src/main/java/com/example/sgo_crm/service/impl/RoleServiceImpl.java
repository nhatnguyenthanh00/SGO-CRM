package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.model.Role;
import com.example.sgo_crm.repository.RoleRepository;
import com.example.sgo_crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleName(String rolename) {
        return roleRepository.findByRoleName(rolename);
    }

    @Override
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }


}
