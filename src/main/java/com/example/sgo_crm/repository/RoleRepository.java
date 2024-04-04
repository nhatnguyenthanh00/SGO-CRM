package com.example.sgo_crm.repository;

import com.example.sgo_crm.DTO.RoleDTO;
import com.example.sgo_crm.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String rolename);

    @Query(value ="SELECT new com.example.sgo_crm.DTO.RoleDTO(role.roleId,role.roleName,role.roleDescription) FROM Role role")
    List<RoleDTO> getAllRole();
}
