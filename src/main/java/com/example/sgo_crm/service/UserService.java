package com.example.sgo_crm.service;

import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.model.User;
import com.example.sgo_crm.request.UserRequest;

public interface UserService {
    void save(User user);

    User addUser(UserRequest request);

    User getUserById(String id);

    User updateUserInfo(String id, UserRequest request);


}
