package com.example.sgo_crm.controller;

import com.example.sgo_crm.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/role")
public class RoleController {
    private final RoleServiceImpl roleService;
    @Autowired
    public RoleController(RoleServiceImpl roleService){
        this.roleService = roleService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRole());
    }
}
