package com.example.sgo_crm.repository;

import com.example.sgo_crm.DTO.UserDTO;
import com.example.sgo_crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getByUsername(@Param("username") String username);
//    @Query("SELECT new com.example.sgo_crm.DTO.UserDTO(u.userId, u.fullname, u.roles) FROM User u")
//    List<UserDTO> findAllUserDTOsWithRoles();
}
