package com.example.sgo_crm.controller;

import com.example.sgo_crm.exception.DataSaveException;
import com.example.sgo_crm.exception.InvalidFormatException;
import com.example.sgo_crm.exception.UsernameExistsException;
import com.example.sgo_crm.model.APIResponse;
import com.example.sgo_crm.model.User;
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

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllUser(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUserDTO());
    }


    @GetMapping("/search")
    public ResponseEntity<?> findUser(@RequestParam(value = "id", required = false) String userId,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "role", required = false) String role){
        List<User> users = userService.findUser(userId,name,role);
        APIResponse apiResponse = APIResponse.builder()
                .statusCode(200)
                .message("Tìm kiếm thành công")
                .data(users).build();
        if(users.isEmpty()) {
            apiResponse.setMessage("Không có kết quả");
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
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
        User user = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().statusCode(201).message("Lưu thành công nhân viên").data(user).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().statusCode(200).message("Lưu thành công nhân viên").data(user).build());
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
        User user = userService.updateUserInfo(id, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.builder().statusCode(201).message("Lưu thành công nhân viên").data(user).build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successful.");
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