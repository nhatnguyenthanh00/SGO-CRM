package com.example.sgo_crm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {

    @NotBlank(message = "Họ và tên không được để trống")
    @Pattern(regexp = "^[a-zA-ZÀ-ỹ\\s]+$", message = "Họ và tên không chứa ký tự đặc biệt")
    private String fullname;

    @NotBlank(message = "Username không được để trống")
    @Size(min = 8, max = 15, message = "Username phải có từ 8-15 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username không chứa khoảng trắng và ký tự đặc biệt")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 8, message = "Password phải có ít nhất 8 ký tự")
    @Pattern(regexp = "\\S+$", message = "Password không được chứa khoảng trắng")
    private String password;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0[1-9][0-9]{8}$", message = "Số điện thoại chỉ gồm 10 chữ số")
    private String phonenumber;

    @NotBlank(message = "Role không được để trống")
    private String role;
}
