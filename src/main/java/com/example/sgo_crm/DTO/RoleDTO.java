package com.example.sgo_crm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTO {
    private Long roleId;
    private String roleName;

    private String roleDescription;
}
