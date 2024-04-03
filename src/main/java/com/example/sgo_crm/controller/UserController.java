package com.example.sgo_crm.controller;

import com.example.sgo_crm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllUser(){
//        userService.findRolesByUserId("0001");
        System.out.println("Call api get all user");
//        if(userService.getAllUser().isEmpty()){
//            return ResponseEntity.ok().body("Chưa có nhân viên nào được thêm vào hệ thống");
//        }
        return ResponseEntity.ok().body(userService.findRolesByUserId("0001"));
    }
}
