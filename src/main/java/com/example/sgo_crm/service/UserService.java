package com.example.sgo_crm.service;

import com.example.sgo_crm.model.User;
import com.example.sgo_crm.request.UserRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void save(User user);

    User addUser(UserRequest request);

    User getUserById(String id);

    User updateUserInfo(String id, UserRequest request);

    Page<User> findUser(String id, String name, String role, int page);

    Page<User> getUsers(int page);
}
