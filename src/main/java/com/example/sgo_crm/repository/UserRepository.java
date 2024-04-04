package com.example.sgo_crm.repository;

import com.example.sgo_crm.DTO.UserDTO;
import com.example.sgo_crm.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT u FROM User u WHERE u.phonenumber = :phone")
    User findByPhonenumber(@Param("phone") String phonenumber);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN u.roles r " +
            "WHERE (:id IS NULL OR :id = '' OR u.userId LIKE %:id%) AND " +
            "(:name IS NULL OR :name = '' OR u.fullname LIKE %:name%) AND " +
            "(:role IS NULL OR :role = '' OR r.roleName LIKE %:role%)")
    Page<User> findUser(@Param("id") String id, @Param("name") String name, @Param("role") String role, Pageable pageable);
}
