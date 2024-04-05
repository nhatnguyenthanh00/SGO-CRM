package com.example.sgo_crm.service;

import com.example.sgo_crm.DTO.RoleDTO;
import com.example.sgo_crm.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findByRoleName(String rolename);

//    List<RoleDTO> getAllRoleDTO();

    List<Role> getAllRole();
}
