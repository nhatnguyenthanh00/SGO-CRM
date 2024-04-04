package com.example.sgo_crm.DTO;

import com.example.sgo_crm.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String fullname;
    private Set<String> roles;

    public void setRoles(Set<Role> roles) {
        this.roles = roles.stream()
                .map(Role::getRoleName) // Lấy tên của mỗi Role
                .collect(Collectors.toSet()); // Thu thập vào một Set<String>
    }
}
