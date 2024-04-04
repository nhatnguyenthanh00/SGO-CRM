package com.example.sgo_crm.service;

import com.example.sgo_crm.model.User;
import com.example.sgo_crm.request.UserRequest;

import java.util.List;

public interface UserService {
    void save(User user);

    void addUser(UserRequest request);

    User getUserById(String id);

    void updateUserInfo(String id, UserRequest request);

    List<User> getAllUser();
}
