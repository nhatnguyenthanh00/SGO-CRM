package com.example.sgo_crm.service;

import com.example.sgo_crm.model.User;
import com.example.sgo_crm.request.AddUserRequest;

import java.util.List;

public interface UserService {
    void save(User user);

    void addUser(AddUserRequest request);

    User getUserById(String id);

    List<User> getAllUser();
}
