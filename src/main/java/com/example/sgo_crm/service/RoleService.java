package com.example.sgo_crm.service;

import com.example.sgo_crm.DTO.RoleDTO;
import com.example.sgo_crm.model.Role;

import java.util.List;

public interface RoleService {

    Role findByRoleName(String rolename);

//    List<RoleDTO> getAllRoleDTO();

    List<Role> getAllRole();
}
