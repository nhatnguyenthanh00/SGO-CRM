package com.example.sgo_crm.service.impl;

import com.example.sgo_crm.DTO.UserDTO;
import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.model.Role;
import com.example.sgo_crm.model.User;
import com.example.sgo_crm.repository.UserRepository;
import com.example.sgo_crm.request.UserRequest;
import com.example.sgo_crm.service.UserService;
import com.example.sgo_crm.util.AppConstants;
import com.example.sgo_crm.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleServiceImpl roleService;

    private final Validate validate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleServiceImpl roleService, Validate validate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.validate = validate;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User addUser(UserRequest request) {

        validate.isValidData(request);

        if(userRepository.getByUsername(request.getUsername()) != null) {
            throw new UsernameExistsException(AppConstants.USERNAME_IS_ALREADY_EXIST);
        }

        if(userRepository.findByPhonenumber(request.getPhonenumber()) != null) {
            throw new UsernameExistsException(AppConstants.PHONENUMBER_IS_ALREADY_EXIST);
        }

        try {

            Set<Role> roleSet = new HashSet<>();
            for(String role: request.getRoles()) {
                roleSet.add(roleService.findByRoleName(role));
            }

            User user = User.builder()
                    .fullname(request.getFullname())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phonenumber(request.getPhonenumber())
                    .roles(roleSet)
                    .build();

            save(user);

            return user;
        }catch (DataSaveException e) {
            throw new DataSaveException(AppConstants.ERROR_SAVING);
        }
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUserInfo(String id, UserRequest request) {

        validate.isValidData(request);

        try {

            User user = userRepository.findById(id).get();

            if(userRepository.getByUsername(request.getUsername()) != null) {
                throw new UsernameExistsException(AppConstants.USERNAME_IS_ALREADY_EXIST);
            }

            if(userRepository.findByPhonenumber(request.getPhonenumber()) != null) {
                throw new UsernameExistsException(AppConstants.PHONENUMBER_IS_ALREADY_EXIST);
            }

            if(!user.getFullname().equals(request.getFullname())) {
                user.setFullname(request.getFullname());
            }

            if(!user.getUsername().equals(request.getUsername())) {
                user.setUsername(request.getUsername());
            }

            if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }

            if(!user.getPhonenumber().equals(request.getPhonenumber())) {
                user.setPhonenumber(request.getPhonenumber());
            }

            // update role
            Set<Role> roleSet = new HashSet<>();
            for(String role: request.getRoles()) {
                roleSet.add(roleService.findByRoleName(role));
            }

            user.setRoles(roleSet);

            save(user);

            return user;

        }catch (DataSaveException e) {
            throw new DataSaveException(AppConstants.ERROR_SAVING);
        }
    }


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    public Page<User> findUser(String id, String name, String role, int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        return userRepository.findUser(id.trim(), name.trim(), role.trim(), pageable);
    }

    public List<UserDTO> getAllUserDTO(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user : userList){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setFullname(user.getFullname());
            userDTO.setRoles(user.getRoles());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }
}
