package com.example.sgo_crm.service;

import com.example.sgo_crm.model.Role;

public interface RoleService {

    Role findByRoleName(String rolename);
}
