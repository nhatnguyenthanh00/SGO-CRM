package com.example.sgo_crm.DTO;

import com.example.sgo_crm.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {

    private String userId;
    private String fullname;
    private Set<RoleDTO> roles;
}
