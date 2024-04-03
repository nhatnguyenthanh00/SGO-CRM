package com.example.sgo_crm.service;

import com.example.sgo_crm.DTO.UserDTO;
import com.example.sgo_crm.model.Role;
import com.example.sgo_crm.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(User user);

    List<UserDTO> getAllUser();

    Set<Role> findRolesByUserId(String userId);
}
