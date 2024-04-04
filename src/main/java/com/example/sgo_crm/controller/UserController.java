package com.example.sgo_crm.controller;

import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.request.UserRequest;
import com.example.sgo_crm.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest request,
                                     BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }
        userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lưu thành công nhân viên");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id,
                                        @Valid @RequestBody UserRequest request,
                                        BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }
        userService.updateUserInfo(id, request);
        return ResponseEntity.status(HttpStatus.OK).body("Lưu thành công nhân viên");
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<?> handleUsernameExistsException(UsernameExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(DataSaveException.class)
    public ResponseEntity<?> handleDataSaveException(DataSaveException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
