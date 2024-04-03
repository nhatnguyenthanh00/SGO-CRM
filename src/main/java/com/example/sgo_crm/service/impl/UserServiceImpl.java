package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.model.Role;
import com.example.sgo_crm.model.User;
import com.example.sgo_crm.repository.UserRepository;
import com.example.sgo_crm.request.AddUserRequest;
import com.example.sgo_crm.service.RoleService;
import com.example.sgo_crm.service.UserService;
import com.example.sgo_crm.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleServiceImpl roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void addUser(AddUserRequest request) {

        if(userRepository.getByUsername(request.getUsername()) != null) {
            throw new UsernameExistsException(AppConstants.USERNAME_IS_ALREADY_EXIST);
        }

        try {

            Set<Role> roleSet = new HashSet<>();
            roleSet.add(roleService.findByRoleName(request.getRole()));

            User user = User.builder()
                    .fullname(request.getFullname())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phonenumber(request.getPhonenumber())
                    .roles(roleSet)
                    .build();

            save(user);
        }catch (DataSaveException e) {
            throw new DataSaveException(AppConstants.ERROR_SAVING);
        }
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }
}
